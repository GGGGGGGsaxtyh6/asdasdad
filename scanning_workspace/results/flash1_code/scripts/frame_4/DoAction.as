function setText(w)
{
   localinformationSetting.text = _root.ls.storageLevels[w].label;
}
function pressSnap(n)
{
   if(slider.active == true)
   {
      slider._x = slider.markList[n] + crib._x;
      localinformationSetting.text = ls.storageLevels[n].label;
      _root.changeKlimit(ls.storageLevels[n].value);
   }
}
frame_localInformation();
killHand();
