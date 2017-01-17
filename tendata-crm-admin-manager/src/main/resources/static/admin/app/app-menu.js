'use strict'

angular.module('app')

    .controller('MenuCtrl', function ($scope, $rootScope, $location, $cookies) {

        // -------------- menu set ------------------//
        $scope.datalists = [

            {
                name: '我的办公桌',
                childrenSign: 0,
                children: [ {
                    name: '电子邮件',
                    href:'emails'
                },
                    {
                        name: '个人考勤',
                        href: 'attendance'
                    }],
                menuIcon: 'fa-desktop'
            },

        ];

        $scope.secMenuToggle = [false, false];

        $scope.toggleSec = function (item) {

            var childrenSign = item.datalist.childrenSign;

            for (var i = 0, j = $scope.secMenuToggle.length; i < j; i++) {
                if (i == item.datalist.childrenSign) {
                    if ($scope.secMenuToggle[i]) {
                        $scope.secMenuToggle[i] = false;
                    } else {
                        $scope.secMenuToggle[i] = true;
                    }
                    continue;
                }
                $scope.secMenuToggle[i] = false;
            }

            //$scope.secMenuToggle[childrenSign] = !$scope.secMenuToggle[childrenSign];

        }

        $scope.select = function (item) {
            if (!item.children) {
                $scope.secSelected = {};
            }
            if ($scope.selected != item) {
                $scope.selected = item;
                $location.url(item.href);
            }

        };

        /*$scope.secSelect = function(item) {
         if ($scope.secSelected == item) {
         $scope.secSelected = item;
         $location.url(item.href);
         }
         }*/

        $scope.isActive = function (item) {
            return $scope.selected === item;
        };

        $scope.isSecActive = function (item) {
            return $scope.secSelected === item;
        };

        $rootScope.setMenuActive = function () {
            var currentPath = $cookies.get('currentPath');
            for (var i = 0, j = $scope.datalists.length; i < j; i++) { // 初始化menu选中
                var data = $scope.datalists[i];
                if (!currentPath) {
                    currentPath = $location.path();
                }

                if (data.children) {
                    for (var m = 0, n = data.children.length; m < n; m++) {
                        if (currentPath === data.children[m].href) {
                            $scope.secSelected = data.children[m];
                            $scope.secMenuToggle[data.childrenSign] = true;
                            $scope.selected = data;
                            break;
                        }
                    }
                } else {
                    if (currentPath === data.href) {
                        $scope.selected = data;
                        break;
                    }
                }
            }
        }
        // -------------- end menu set ------------------//

    });