$(function() {
	var link = document.createElement('link');
	link.rel = 'stylesheet';
	link.type = 'text/css';
	link.href = 'css/cart.css';
	link.media = 'all';
	var cartid =$("#cartid").data("ref");
	document.getElementsByTagName('head')[0].appendChild(link);

	$(".addToCart").click(function(e) {
		
		var ref = $(this).data("ref");
		$.ajax({
			method : 'POST',
			url : "cart/ref/add.json",
			dataType : "json",
			contentType : 'application/json',
			data : JSON.stringify({
				id : ref,
				qty : 1
			})
		}).done(function(data) {
			
			if (data.status == 'OK') {
			  console.log(data)
				$.ajax({
					url : "cart/cartid.html"
				}).done(function(data) {
					JSON.stringify($('#cartInHeader').html(data))
				});
			}
		});

	});

	$.ajax({
		url : "cart/cartid.html"
	}).done(function(data) {
		JSON.stringify($('#cartInHeader').html(data))
	});
});