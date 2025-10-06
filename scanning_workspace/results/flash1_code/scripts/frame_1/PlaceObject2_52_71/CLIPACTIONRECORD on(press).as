on(press){
   this.always = !this.always;
   _parent.onChange(this._name,this.always);
}
