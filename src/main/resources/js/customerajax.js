$('table tbody').html('');
var name  = $('#name').val();
var title = $('#title').val();

$('#sub').click(function (){
    var name  = $('#name').val();
    console.log(name);
})
console.log(name);
$.ajax({
    url:"/customer/all",
    type:"get",
    datatype:'json',
    success:function(res){
        console.log(res);
        //将数据显示在页面上
        var str = "";
        str +="<table class=\"table\"><thead><tr><th>id</th><th>姓名</th></tr></thead><tbody>";
        //遍历数据
        $.each(res.resultData,function(index,element){
            console.log(element);
            str +="<tr><td>"+element.id+"</td><td>"+element.customerName+"</td></tr>";
        })
//遍历完成之后
        str +="</tbody></table>";
//将表格添加到body中
        $('body').append(str);
    }
})