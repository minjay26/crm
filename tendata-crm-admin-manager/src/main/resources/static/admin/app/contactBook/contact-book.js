/**
 * Created by Administrator on 2017/1/13.
 */
'user strict'

angular.module('app')

    .controller('ContactBookCtrl', function ($scope, $rootScope, $location, $http, $routeParams, $window, $cookies, optionUrl) {
        getUsers("");
        function getUsers(status) {
            $http.get(optionUrl.getUsers, {
                withCredentials: true,
                params: {"status": status},
            }).success(function (data) { // 成功从后台获取数据
                $scope.users = data;
            }).error(function () {
                $scope.openAlert('danger', '网络异常，请稍后再试或者联系管理员', 5000);
            });
        }


        $scope.getStatusUser = function (status) { // 从后台获取数据
            getUsers(status);
        }

        $scope.beginChat = function (user) {
            $location.url("/chat?user="+user.username)
        }


    })


