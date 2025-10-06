on(press){
   this.gotoAndStop(3);
   _parent.Allow.gotoAndStop(1);
   _parent.domainSettings.data.allow = false;
   _parent.AlertResponse = "allow";
}
