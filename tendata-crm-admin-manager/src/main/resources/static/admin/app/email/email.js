'user strict'

angular.module('app')

    .controller('MailController', function ($scope, $rootScope, FileUploader, $location, $http, $routeParams, $window, $cookies, optionUrl) {
        $scope.currentPage = 1;
        $scope.maxSize = 6;
        $scope.itemsPerPage = 10;
        $scope.dt = {
            startDate: null,
            endDate: null
        }
        $scope.isHide = false;
        $scope.loading = false;

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

        $scope.changeCategory = function (e) {
            var index = e.value;
            getData(index);
        }

        function getData(index) { // 从后台获取数据
            $scope.loadDivVisible = true;
            $http.get(optionUrl.mails, {
                withCredentials: true,
                params: {
                    category :index,
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

        $scope.submit=function () {
            var toUser = $scope.submit_toUser,
                title = $scope.submit_title,
                content = $scope.submit_content;
        }

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
            $rootScope.editModel = item;
            $http.get(optionUrl.mailRead, {
                withCredentials: true,
                params: {
                    id: item.id,
                }
            })


            $location.url('emails/edit?id=' + item.id);

        }

        $scope.goBackMails = function(){
            $location.url('/emails');
        }


        $scope.uploader = new FileUploader({
            url: optionUrl.upload,
            queueLimit: 2
        });

        $scope.beginReply = function () {
            $scope.isHide = !$scope.isHide;
        }




       $scope.reply = function(){
           $http({
               method: "post",
               url: optionUrl.mailSubmit,
               data: {
                   fromMailId: $rootScope.editModel.id,
                   content: $scope.replyContent,
                   title: $scope.title,
                   toUser: $rootScope.editModel.fromUser.id,
               },
               headers: {
                   'Content-Type': 'application/json;charset=utf-8'
               }
           }).success(function (data, status) { // 成功与后台完成交互
               $scope.isHide = false;
               $scope.replyContentShow = true;

           }).error(function (data, status) {
               $scope.replyMsg = "邮件发送失败，请重试";
           });
       }

        if (!$scope.items) {
            getData();
        }
    })


