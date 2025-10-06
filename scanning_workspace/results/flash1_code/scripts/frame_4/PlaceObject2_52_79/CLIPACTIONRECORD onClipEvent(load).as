onClipEvent(load){
   _parent.tout("psd " + _parent.domainSettings.data.klimit);
   if(_parent.domainSettings.data.klimit == -1)
   {
      _parent.slider.active = false;
      _parent.slider._x = _parent.crib._x;
      _parent.slider._alpha = 75;
      _parent.crib._alpha = 30;
      this.gotoAndStop(2);
   }
   else
   {
      _parent.slider.active = true;
      this.gotoAndStop(1);
      _parent.slider._alpha = 100;
      _parent.crib._alpha = 100;
   }
}
