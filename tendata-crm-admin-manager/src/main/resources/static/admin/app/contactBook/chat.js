/**
 * Created by Administrator on 2017/1/13.
 */
'user strict'

angular.module('app')
    .controller('ChatCtrl', function ($scope, $rootScope, $location, $http, $routeParams, $window, $cacheFactory, optionUrl) {
        $scope.recentChatDisplay = false;
        $scope.contactBookDisplay = false;

        $scope.currentChatUser = $routeParams.user;


        if (sessionStorage.recentChatUsers) {
            if (sessionStorage.recentChatUsers.split(',').indexOf($scope.currentChatUser) < 0) {
                sessionStorage.recentChatUsers = sessionStorage.recentChatUsers + ',' + $scope.currentChatUser;
            }
        } else {
            sessionStorage.recentChatUsers = $scope.currentChatUser;
        }
        $scope.recentChatUsers = sessionStorage.recentChatUsers.split(',').reverse();

        var currentUserChatCache = sessionStorage.getItem($scope.currentChatUser)
        if (currentUserChatCache) {
            $scope.recentChatRecords = JSON.parse(currentUserChatCache).reverse();
        }

        var sock = new SockJS("/endpointChat"),
            stomp = Stomp.over(sock);
        stomp.connect('guest', 'guest', function (frame) {
            stomp.subscribe('/user/queue/notifications', handleNotification)
        })

        getOnlineUser();
        function getOnlineUser() {
            $http.get(optionUrl.getUsers, {
                withCredentials: true,
                params: {"status": 1},
            }).success(function (data) { // 成功从后台获取数据
                $scope.onlineUsers = data;
            }).error(function () {
                $scope.openAlert('danger', '网络异常，请稍后再试或者联系管理员', 5000);
            });
        }

        function handleChatRecordCache(left, content) {
            var record = {
                left: left,
                content: content,
            }
            var recentChatRecordData = new Array();
            recentChatRecordData.push(record);
            if (currentUserChatCache) {
                Array.prototype.push.apply(recentChatRecordData, JSON.parse(currentUserChatCache));
            }
            sessionStorage.setItem($scope.currentChatUser, JSON.stringify(recentChatRecordData));
        }

        function handleNotification(message) {
            var content = JSON.parse(message.body).content;
            angular.element(".chat-thread").append(returnMsgHtml(content));
            handleChatRecordCache(true, content);
            $scope.$apply();
        }

        function returnMsgHtml(content) {
            return "<li class='chat-li chat-li-left'>" + content + "</li>";
        }

        function returnSendMsgHtml(content) {
            return "<li class='chat-li chat-li-right'>" + content + "</li>";
        }

        $scope.recentChat = function () {
            $scope.contactBookDisplay = false;
            $scope.recentChatDisplay = !$scope.recentChatDisplay;
        }

        $scope.contactBook = function () {
            $scope.recentChatDisplay = false;
            $scope.contactBookDisplay = !$scope.contactBookDisplay;
        }

        $scope.sendMessage = function () {
            var content = $scope.replyContent;
            $scope.replyContentClass = 'info';
            if (!content) {
                $scope.replyContent = "发送内容不能为空";
                $scope.replyContentClass = 'error';
            }
            var message = {
                "content": content,
                "toUser": $scope.currentChatUser
            }
            handleChatRecordCache(false, content);
            angular.element(".chat-thread").append(returnSendMsgHtml(content));
            $scope.replyContent = "";

            stomp.send("/message", {}, JSON.stringify(message));
        }

        $scope.replyFocus = function () {
            if ($scope.replyContentClass == 'error') {
                $scope.replyContent = "";
                $scope.replyContentClass = "info";
            }
        }
    })


