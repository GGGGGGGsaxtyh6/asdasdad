onClipEvent(load){
   if(_parent.domainSettings.data.allow == false || _parent.domainSettings.data.allow == undefined)
   {
      this.gotoAndStop(3);
   }
}
