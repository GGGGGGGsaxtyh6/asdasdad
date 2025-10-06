on(press){
   this.gotoAndStop(3);
   _parent.Deny.gotoAndStop(1);
   _parent.domainSettings.data.allow = true;
   _parent.AlertResponse = "allow";
}
