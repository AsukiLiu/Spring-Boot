var app = angular.module('myApp', ['ngTable', 'ngResource', 'ngDialog']);

app.controller('myCtrl', function($scope, NgTableParams, $resource, ngDialog) {

    $scope.resource = $resource("api/feed/:feedId",{feedId:'@id'});
    $scope.tableParams = new NgTableParams({}, {
        getData: function(params) {
            var queryParams = {
                page:params.page() - 1,
                size:params.count()
            };

            var sortingProp = Object.keys(params.sorting());
            if(sortingProp.length == 1){
                queryParams["sort"] = sortingProp[0];
                queryParams["sortDir"] = params.sorting()[sortingProp[0]];
            }

            return $scope.resource.query(queryParams, function(data, headers) {
                var totalRecords = headers("Paging-Info");
                params.total(totalRecords);
                return data;
            }).$promise;
        }
    });

    $scope.feed = {name:"", url:""};
    $scope.feeds = $resource("api/feed/:feedId", {feedId:'@id'},{
        'update': {method:'PUT'}
    });

    $scope.createFeed = function(){
        $scope.feeds.save($scope.feed, function(){
            $scope.tableParams.reload();
        });
    }
    $scope.updateFeed = function(){
        $scope.feeds.update($scope.feed, function(){
            $scope.tableParams.reload();
        });
    }
    $scope.deleteFeed = function(id){
        $scope.feeds.delete({feedId:id}, function(){
            $scope.tableParams.reload();
        });
    }

    $scope.addNewFeed = function(){
        $scope.feed = {name:"New Feed", url:"www.example.com"};
        ngDialog.open({template:'templateId', scope:$scope});
    }
    $scope.editFeed = function(row){
        $scope.feed.id = row.id;
        $scope.feed.name = row.name;
        $scope.feed.url = row.url;
        ngDialog.open({template:'templateId', scope:$scope});
    }
    $scope.save = function(){
        ngDialog.close();
        if(!$scope.feed.id){
            $scope.createFeed();
        } else{
            $scope.updateFeed();
        }
    }
});

// Error Handling
app.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.interceptors.push(function ($q, $rootScope) {
        return {
            'responseError': function (responseError) {
                $rootScope.message = responseError.data.message;
                return $q.reject(responseError);
            }
        };
    });
}]);
