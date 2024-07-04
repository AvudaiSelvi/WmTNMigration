app.service('myserv', function() {
          this.getServiceList = function () {
    return [];
}
this.getISEndpoint = function() { 
 return 'http://VMTNWIN11.eur.ad.sag:5555/';
}
this.getAPIList = function() { 
 return [];
}
this.getCreatedTime = function() { 
 return "08-04-2024 23:25:12 IST";
}
this.getPackageInfo = function(){
 return{"packageName":"WmTNMigration","createdDate":"15-09-2023 20:31:11 IST","version":"1.0"};
}
});
