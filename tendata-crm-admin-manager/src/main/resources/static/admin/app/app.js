'use strict'

/**
 * 使用angularjs1.4.7与bootstrap3.3.2用于此次开发表示已经放弃了ie9以下版本的ie浏览器，建议使用chrome, firefox
 */




var app = angular.module('app', [
        'ngRoute',
        'ui.bootstrap',
        'ngCookies',
        'toggle-switch',
        'angularFileUpload'
    ])
    .config(function ($httpProvider) {
        // var header = angular.element("meta[name='_csrf_header']").attr("content");
        // var token = angular.element("meta[name='_csrf']").attr("content");
        // $httpProvider.defaults.headers.common[header] = token;
    })

    .config(function ($routeProvider) { // 用于页面跳转

        $routeProvider.when('/emails', {
            templateUrl: 'app/email/email.html',
            controller: 'MailController'
        }).when('/emails/edit', {
            templateUrl: 'app/email/preview.html',
            controller: 'MailController'
        }).when('/customer_service/delete', {
            templateUrl: 'app/customer_service/customer_service_delete.html',
            controller: 'CustomerServiceDeleteCtrl'
        }).when('/customer_service/answer', {
            templateUrl: 'app/customer_service/customer_service_edit.html',
            controller: 'CustomerServiceAnswerCtrl'
        });
    })

    .controller('InitCtrl', function ($scope, $http, $rootScope, optionUrl, $cookies, $interval) {
        // console.log('app init...'); 会导致ie9停止运行
        if (window.console) { // 检查是否为ie9此种奇葩版本浏览器
            var startMsg = 'app initializing...\n';
            startMsg = startMsg + '\n' +
                '                  _oo8oo_\n' +
                '                 o8888888o\n' +
                '                 88" . "88\n' +
                '                 (| -_- |)\n' +
                '                 0\\  =  /0\n' +
                '               ___/\'===\'\\___\n' +
                '             .\' \\\\|     |// \'.\n' +
                '            / \\\\|||  :  |||// \\\n' +
                '           / _||||| -:- |||||_ \\\n' +
                '          |   | \\\\\\  -  /// |   |\n' +
                '          | \\_|  \'\'\\---/\'\'  |_/ |\n' +
                '          \\  .-\\__  \'-\'  __/-.  /\n' +
                '        ___\'. .\'  /--.--\\  \'. .\'___\n' +
                '     ."" \'<  \'.___\\_<|>_/___.\'  >\' "".\n' +
                '    | | :  `- \\`.:`\\ _ /`:.`/ -`  : | |\n' +
                '    \\  \\ `-.   \\_ __\\ /__ _/   .-` /  /\n' +
                '=====`-.____`.___ \\_____/ ___.`____.-`=====\n' +
                '                  `=---=`\n' +
                '\n' +
                '\n' +
                '~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n' +
                '\n' +
                '      佛祖保佑     永不宕机     永无臭虫\n' +
                '      上帝保佑     程序员们     身体健康';
            console.log(startMsg);
        }
        $rootScope.positiveIntReg = /^\+?[1-9]\d*$/;
        $rootScope.loadDivVisible = true;
    })

    .controller('ModalAlertCtrl', function ($scope, $uibModal, optionUrl) {
        $scope.globalUrl = optionUrl;
        $scope.openModal = function (size, templateUrl, msg, itemId) { // 大小
            $uibModal.open({
                animation: true,
                templateUrl: templateUrl,
                controller: 'ModalInstanceCtrl',
                size: size,
                resolve: {
                    params: {
                        currentId: itemId,
                        hTitle: msg
                    }
                }
            });

        };
    })

    .controller('ModalInstanceCtrl', function ($scope, $uibModalInstance, $http, params) {
        $scope.currentId = params.currentId;
        $scope.hTitle = params.hTitle;
        $scope.ok = function () {
            $uibModalInstance.close($scope.selected.item);
        };

        $scope.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };
    })

    .controller('AlertCtrl', function ($scope, $rootScope, $timeout) {
        $rootScope.alerts = [];

        $rootScope.closeAlert = function (index) {
            $scope.alerts.splice(index, 1);
        };

        $rootScope.openAlert = function (type, msg, ms) { // 类型： info danger... | 消息  |  显示时间（单位毫秒， 默认2000）
            if (!ms) {
                ms = 2000;
            }
            $scope.alerts.push({
                type: type,
                msg: msg
            });
            $timeout(function () {
                $scope.closeAlert();
            }, ms);
        };

        var imageFormats = ["image/jpeg", "image/jpg", "image/bmg", "image/pcx", "image/gif", "image/fpx", "image/svg", "image/bmp", "image/png"];
        $rootScope.validationIsImage = function (format) {
            if ($.inArray(format, imageFormats) >= 0) {
                return true;
            } else {
                return false;
            }
        }

    })

    .factory("transformRequestAsFormPost", function () { // 未使用
        return function serializeData(data) {
            if (!angular.isObject(data)) {
                return ((data == null) ? "" : data.toString());
            }

            var buffer = [];
            for (var name in data) {
                if (!data.hasOwnProperty(name)) {
                    continue;
                }
                var value = data[name];
                if (name == 'createdDate' || name == 'lastModifiedDate' || name == 'replyDate') {
                    value = parseInt(value);
                }
                buffer.push(
                    encodeURIComponent(name) + "=" +
                    encodeURIComponent((value == null) ? "" : value)
                );
            }

            return buffer.join("&").replace(/%20/g, "+");
        }
    })

    .factory('ErrorsService', function ($rootScope) {
        var Errors = {};
        Errors.parse = function (data) {
            var fieldErrors = data.fieldErrors;
            var errorContent = '';

            for (var i = 0; i < fieldErrors.length; i++) {
                var fieldName = fieldErrors[i].field;
                errorContent = errorContent + format(fieldName) + fieldErrors[i].message + '； ';
            }

            $rootScope.openAlert('danger', errorContent);
        };


    });