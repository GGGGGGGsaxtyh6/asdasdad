onClipEvent(load){
   if(_parent.domainSettings.data.always == undefined)
   {
      this.always = false;
   }
   else
   {
      this.always = _parent.domainSettings.data.always;
   }
   if(this.always == true)
   {
      this.gotoAndStop(2);
   }
   else
   {
      this.gotoAndStop(1);
   }
}
