'user strict'

angular.module('app')

    .controller('MailController', function ($scope, $rootScope,$location,$http,$routeParams, $window, $cookies, optionUrl) {
        $scope.currentPage = 1;
        $scope.maxSize = 6;
        $scope.itemsPerPage = 10;
        $scope.dt = {
            startDate: null,
            endDate: null
        }

        $scope.filterOptions = {
            availableOptions: [
                {value: '1', name: '未读邮件'},
                {value: '2', name: '收件箱'},
                {value: '3', name: '草稿箱'},
                {value: '4', name: '已发送'},
                {value: '5', name: '外发邮件箱'},
                {value: '6', name: '已删除'},
                {value: '7', name: '查询邮件'},
            ],
            selectedOption: {value: '1', name: '未读邮件'}
        };

        function getData() { // 从后台获取数据
            $scope.loadDivVisible = true;
            $http.get(optionUrl.mails, {
                withCredentials: true,
                params: {
                    page: $scope.currentPage - 1,
                }
            }).success(function (data) { // 成功从后台获取数据
                $scope.totalItems = data.totalElements;
                $scope.items = data.content;
                $scope.loadDivVisible = false;
            }).error(function () {
                $scope.openAlert('danger', '网络异常，请稍后再试或者联系管理员', 5000);
                $scope.loadDivVisible = false;
            });
        }

        $scope.pageChanged = function () { // 单击页码按钮实现数据动态变化
            getData();
        };

        $scope.search = function () {
            getData();
        };

        $scope.getById = function (id) {
            var url = optionUrl.mail;
            url = url.replace('{id}', id);
            $http.get(url).success(function (data) { // 成功从后台获取数据
                $scope.previewMail(data);
            }).error(function () {
                $scope.openAlert('danger', '网络异常，请稍后再试或者联系管理员', 5000);
            });
        }
        if (!$rootScope.editModel) { // 在更新页面刷新的时候可以根据id重新找到服务器的数据，否则所有数据都清空
            var id = $routeParams.id;
            if (id) {
                $scope.getById(id);
            } else {
                getData(); // 初始化list数据
            }
        } else {
            if ($location.path().indexOf('edit') < 0)
                getData(); // 初始化list数据
        }

        $scope.previewMail=function (item) {
            $rootScope.mail = {};
            $scope.mail.title = '编辑通道';
            $rootScope.editModel = item;
            $location.url('emails/edit?id=' + item.id);

        }

        $scope.goBackMails = function(){
            $location.url('/emails');
        }

        $(document)     //图片上传点击事件
            .on('change',
                '#file',
                function () {
                    btnUpload();
                });


        var btnUpload = function () {
            var token = $scope.token;
            var file = {
                name: '',
                size: '',
                type: '',
                sourceLink: ''
            };
            $scope.fileSizeError = false;
            $scope.uploadBtn = false;
            var Qiniu_upload = function (f, token, key) {
                $scope.uploadBtn = false;

                var fileName = f.name;
                var fileSize = (f.size) / 1024;
                var fileType = f.type;
                var format = fileName.slice(fileName.lastIndexOf(".") + 1).toLowerCase(),
                    typeArr = ['jpg', 'png', 'gif', 'doc', 'docx', 'xlsx', 'csv', 'xls', 'pdf', 'rar', 'zip'];

                if (typeArr.indexOf(format) === (-1)) {
                    $scope.fileSizeError = true;
                    $scope.error = '文件格式错误';
                    $scope.uploadBtn = true;
                    $("#file").val('');
                    $scope.$apply();
                } else if (fileSize > 5 * 1024 || $scope.files >= 3) {
                    $scope.fileSizeError = true;
                    $scope.error = '每个文件限制大小在5MB以内';
                    $scope.uploadBtn = true;
                    $("#file").val('');
                    $scope.$apply();
                } else {

                    var xhr = new XMLHttpRequest();
                    xhr.open('POST', qiNiu.QI_NIU_UPLOAD_URL, true);
                    var formData, startDate;
                    formData = new FormData();
                    if (key !== null && key !== undefined) formData.append('key', key);
                    formData.append('token', $scope.token);
                    formData.append('file', f);
                    var taking;
                    xhr.upload.addEventListener("progress", function (evt) {
                        if (evt.lengthComputable) {
                            $scope.fileSizeError = true;
                            $scope.error = '文件正在上传中。。。。。。';

                            var nowDate = new Date().getTime();
                            taking = nowDate - startDate;
                            var x = (evt.loaded) / 1024;
                            var y = taking / 1000;
                            var uploadSpeed = (x / y);
                            var formatSpeed;
                            if (uploadSpeed > 1024) {
                                formatSpeed = (uploadSpeed / 1024).toFixed(2) + "Mb\/s";
                            } else {
                                formatSpeed = uploadSpeed.toFixed(2) + "Kb\/s";
                            }
                            var percentComplete = Math.round(evt.loaded * 100 / evt.total);
                            if (percentComplete != 100) {
                                $scope.uploadBtn = false;
                                $scope.$apply();
                            } else {
                                $scope.fileSizeError = false;
                                $scope.uploadBtn = true;
                                if ($scope.files.length == 2)
                                    $scope.uploadBtn = false;
                                $scope.$apply();
                            }
                            $scope.$apply();
                        }
                    }, false);

                    xhr.onreadystatechange = function (response) {
                        if (xhr.readyState == 4 && xhr.status == 200 && xhr.responseText != "") {
                            $scope.repeatBtn = true;
                            var blkRet = JSON.parse(xhr.responseText);
                            $scope.isHaveUpLoadFile = true;
                            file.sourceLink = qiNiu.DOMAIN + blkRet.key;
                            file.name = fileName;
                            file.size = fileSize;
                            file.type = format;
                            saveAttachment(file);
                            file.size = convertFilesize(fileSize);
                            upLoadFiles.push(file);
                            $scope.files = upLoadFiles;
                            $scope.isHaveUpLoadFile = true;
                            $scope.uploadBtn = true;
                            if ($scope.files.length >= 3) {
                                $scope.uploadBtn = false;
                                $scope.$apply();
                            }
                            $("#file").val('');
                            $scope.$apply();
                            console && console.log(blkRet);
                        } else if (xhr.status != 200 && xhr.responseText) {

                        }
                    };

                    startDate = new Date().getTime();
                    $("#progressbar").show();
                    xhr.send(formData);
                }
            };
            if ($("#file")[0].files.length > 0 && $scope.token != "") {
                if ($("#file")[0].files.length + $scope.files.length > 3) {
                    $scope.fileSizeError = true;
                    $scope.error = '最多上传三个文件';
                    $("#file").val('');
                    $scope.$apply();
                    return;
                }
                Qiniu_upload($("#file")[0].files[0], $scope.token, new Date().format('yyyyddMMhhmmss') + $("#file")[0].files[0].name);

            } else {
                console && console.log("form input error");
            }
        }

       $scope.reply = function(){
           alert($scope.replyContent);
       }

        if (!$scope.items) {
            getData();
        }
    })


