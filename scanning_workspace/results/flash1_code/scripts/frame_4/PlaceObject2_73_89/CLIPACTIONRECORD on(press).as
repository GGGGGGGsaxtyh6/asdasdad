on(press){
   if(this.active)
   {
      this.gotoAndStop(2);
      startDrag(this,0,left,top,right,bottom);
      pressed = true;
   }
}
