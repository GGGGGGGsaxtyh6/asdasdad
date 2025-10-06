on(releaseOutside,release){
   if(this.active)
   {
      stopDrag();
      this.gotoAndStop(1);
      pressed = false;
      i = 1;
      while(i <= markers)
      {
         markXpos = eval("mark" + i + "Xpos");
         if(this._x - _parent.crib._x / 2 > markXpos - snapDistance && this._x - _parent.crib._x / 2 <= markXpos + snapDistance)
         {
            this._x = markXpos + _parent.crib._x;
            _parent.localinformationSetting.text = _root.ls.storageLevels[i].label;
            _root.changeKlimit(_root.ls.storageLevels[i].value);
            break;
         }
         i++;
      }
   }
}
