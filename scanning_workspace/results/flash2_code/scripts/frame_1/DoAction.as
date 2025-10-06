this.data = new Object();
this.GetSO = function(domain)
{
   var soname = "settings";
   if(domain != null)
   {
      soname = domain + "/" + soname;
   }
   if(this.data[soname] == null)
   {
      this.data[soname] = SharedObject.getLocal(soname,"/support/flashplayer/sys");
   }
   return this.data[soname];
};
this.LogDomain = function(domain)
{
   if(domain != null)
   {
      var SO = GetSO(null);
      if(SO.data.domains == null)
      {
         SO.data.domains = new Object();
      }
      if(SO.data.domains[domain] == null)
      {
         SO.data.domains[domain] = true;
         SO.flush();
      }
   }
};
this.GetSetting = function(domain, name)
{
   var SO = GetSO(domain);
   return SO.data[name];
};
this.SetSetting = function(domain, name, value, override)
{
   var SO = GetSO(domain);
   if(override || SO.data[name] == null)
   {
      SO.data[name] = value;
      SO.flush();
   }
   LogDomain(domain);
};
this.Commit = function(domain)
{
   var playerSO = GetSO(null);
   playerSO.flush();
   var domainSO = GetSO(domain);
   domainSO.flush();
};
