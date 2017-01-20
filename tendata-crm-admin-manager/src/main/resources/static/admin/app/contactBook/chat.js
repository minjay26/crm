/**
 * Created by Administrator on 2017/1/13.
 */
'user strict'

angular.module('app')

    .controller('ChatCtrl', function ($scope, $rootScope, $location, $http, $routeParams, $window, $cookies, optionUrl) {
        $scope.chatMenuDisplay = false;
         getOnlineUser();
        function  getOnlineUser() {
            $http.get(optionUrl.getUsers, {
                withCredentials: true,
                params: {"status": 1},
            }).success(function (data) { // 成功从后台获取数据
                $scope.onlineUsers = data;
            }).error(function () {
                $scope.openAlert('danger', '网络异常，请稍后再试或者联系管理员', 5000);
            });
        }

    })


