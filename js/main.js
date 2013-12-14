var user_email = "lucaiannario@gmail.com";

var friends_array = [];
var recipes = {};

$(function() {

	init();

	$("#search_form").submit(function(event) {
		event.preventDefault();
		//console.log($(this).serialize());
		onSubmit(this);
	});

	$("#recipe_form").submit(function(event) {
		event.preventDefault();
		console.log($(this).serialize());
	});

});

function init() {

	$("#progressbar_div").hide();
	$("#result_div").hide();

	$.ajax({
	  type: "GET",
	  timeout: 60000,
	  url: "http://socialdinnerapi.herokuapp.com/friends/" + user_email,
	  dataType: "json"
	}).done(function(response) {
		//console.log(response);
		friends_array = response.friends;
		$.each(friends_array, function(i, friend) {
			var li = $('<li class="list-group-item">').html('</span><input type="checkbox" name="friends[]" value="' + friend.email + '"><img class="thumb" src="' + friend.img + '" width="60px" height="60px" /><span>' + friend.name);
			$("#friends_list").append(li);
		});
	}).fail(function(error) {
		// show error alert
		alert("error downloading friends!");
	}).always(function() {
	});

}

function onSubmit(form) {

	$("#search_form_div").fadeOut();
	$("#progressbar_div").fadeIn();

	console.log("http://socialdinnerapi.herokuapp.com/menu/" + user_email + "?" +  $( form ).serialize());
	$.ajax({
	  type: "GET",
	  timeout: 60000,
	  url: "http://socialdinnerapi.herokuapp.com/menu/" + user_email,
	  data: $( form ).serialize(),
	  dataType: "json"
	}).done(function(response) {
		console.log(response);
		recipes = response.recipes;
		populateDishList(recipes["Appetizers"], "appetizers_list", "Appetizers[]");
		populateDishList(recipes["Main Dishes"], "main_list", "Main Dishes[]");
		populateDishList(recipes["Side Dishes"], "side_list", "Side Dishes[]");
		populateDishList(recipes["Desserts"], "dessert_list", "Desserts[]");
		$("#result_div").fadeIn();
	}).fail(function(error) {
		// show error alert
		//alert("search error!");
		$("#search_form_div").fadeIn();
	}).always(function() {
		$("#progressbar_div").hide();
	});

}

function populateDishList(dishes, listId, name) {
	$.each(dishes, function(i, dish) {
		var li = $('<li class="list-group-item">').html('</span><input type="radio" name="' + name + '" value="' + dish.id + '"><img class="thumb" src="' + 
			dish.thumbnail + '" width="60px" height="60px" /><span>' + dish.name + '<br /><i>' + dish.ingredients.join(", ") + '</i>');
		$("#" + listId).append(li);
	});
}
