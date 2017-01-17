/**
 * Created by Administrator on 2017/1/13.
 */
'user strict'

angular.module('app')

    .controller('RegulationController', function ($scope, $rootScope, FileUploader, $location, $http, $routeParams, $window, $cookies, optionUrl) {
        getRegulations();
        function getRegulations() { // 从后台获取数据
            $http.get(optionUrl.regulations, {
                withCredentials: true,
            }).success(function (data) { // 成功从后台获取数据
                $scope.regulations = data;
            }).error(function () {
                $scope.openAlert('danger', '网络异常，请稍后再试或者联系管理员', 5000);
            });
        }

        $scope.check = function (date) {
            var now = new Date();
            var regulationDate = new Date(now.getFullYear() + '-' + now.getMonth() + 1 + '-' + now.getDate() + ' ' + date);
            if (now.getTime() >= regulationDate.getTime() - 0.5 * 60 * 60 * 1000 && now.getTime() < regulationDate.getTime() + 0.5 * 60 * 60 * 1000) {
                return true;
            }
            return false;
        }

        $scope.register = function (item) {
            $http({
                method: 'post',
                url: optionUrl.register,
                params: {"id": item.regulation.id},
            }).success(function () {
                getRegulations();
            }).error(function () {
                $scope.openAlert('danger', '网络异常，请稍后再试或者联系管理员', 5000);
            });
        }

        function getWorkingRecord() {
            $http({
                method: 'get',
                url: optionUrl.register,
            }).success(function () {
                $scope.totalItems = data.totalElements;
                $scope.items = data.content;
                $scope.loadDivVisible = false;
            }).error(function () {
                $scope.openAlert('danger', '网络异常，请稍后再试或者联系管理员', 5000);
            });
        }
    })


