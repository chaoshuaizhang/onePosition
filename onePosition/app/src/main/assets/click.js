<script>
javascript:(function(){
    var objs = document.getElementsByTagName(\"img\");
    for(var i=0;i<objs.length;i++){
        objs[i].onClick=function(){
            window.clickListener.getImage(this.src);
        }
    }
}
</script>
