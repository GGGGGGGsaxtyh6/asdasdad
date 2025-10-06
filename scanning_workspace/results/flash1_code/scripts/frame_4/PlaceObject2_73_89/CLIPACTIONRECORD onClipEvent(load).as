onClipEvent(load){
   left = _parent.crib._x;
   right = left + (_parent.crib._width - (this._width - 1));
   top = this._y;
   bottom = top;
   markers = 6;
   markList = new Array();
   i = 1;
   while(i <= markers)
   {
      set("mark" + i + "Xpos",(i - 1) * ((_parent.crib._width - this._width) / (markers - 1)));
      markList[i] = (i - 1) * ((_parent.crib._width - this._width) / (markers - 1));
      i++;
   }
   snapDistance = (_parent.crib._width - this._width) / markers - 1;
   if(this.active)
   {
      var radVal = _root.getAppropriateLevel(_root.domainSettings.data.klimit);
      i = -1;
      while(i < _root.ls.storageLevels.length)
      {
         if(_root.ls.storageLevels[i].value == radVal)
         {
            _parent.localinformationSetting.text = _root.ls.storageLevels[i].label;
            this._x = eval("mark" + i + "Xpos") + _parent.crib._x;
            break;
         }
         i++;
      }
   }
   else
   {
      this._x = _parent.crib._x;
   }
}
