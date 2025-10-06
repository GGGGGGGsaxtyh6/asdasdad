on(press){
   this.hilite.gotoAndStop(1);
   if(_parent.slider.active == false)
   {
      _parent.slider.active = true;
      this.gotoAndStop(1);
      _parent.changeKlimit(0);
      _parent.slider._alpha = 100;
      _parent.crib._alpha = 100;
   }
   else
   {
      _parent.slider.active = false;
      this.gotoAndStop(2);
      _parent.changeKlimit(-1);
      _parent.tout("psd2 " + _parent.domainSettings.data.klimit);
      _parent.localinformationSetting.text = _parent.ls.storageLevels[1].label;
      _parent.slider._x = _parent.crib._x;
      _parent.slider._alpha = 75;
      _parent.crib._alpha = 30;
   }
}
