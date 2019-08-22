protected void Page_Load(object sender, EventArgs e)
{
  <!--Session.Abandon();-->
  Session.Contents.Remove("result")
}