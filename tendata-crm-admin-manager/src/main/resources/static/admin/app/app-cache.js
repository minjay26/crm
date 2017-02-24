/**
 * Created by minjay on 2017/1/24.
 */
angular.module('app')
    .factory('chatUsersCache', function ($cacheFactory) {
        return $cacheFactory("chatUsersCache")
    })
