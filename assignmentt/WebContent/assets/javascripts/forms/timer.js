/**
 * 
 */

function timeremaining() {
	//alert("ddd");
	//var ddd=${sessionScope.userEntity.getDate()};
	
	//alert(ddd);
	var deadline = new Date("Jun 30, 2019 12:59:00").getTime(); 
	var x = setInterval(function() { 
	var now = new Date().getTime(); 
	var t = deadline - now; 
	var days = Math.floor(t / (1000 * 60 * 60 * 24)); 
	var hours = Math.floor((t%(1000 * 60 * 60 * 24))/(1000 * 60 * 60)); 
	var minutes = Math.floor((t % (1000 * 60 * 60)) / (1000 * 60)); 
	var seconds = Math.floor((t % (1000 * 60)) / 1000); 
	document.getElementById("demo").innerHTML = days + "d "  
	+ hours + "h " + minutes + "m " + seconds + "s "; 
	    if (t < 0) { 
	        clearInterval(x); 
	        document.getElementById("demo").innerHTML = "TIME EXPIRED"; 
	    } 
	}, 1000); 
}


function timer1()
{
	
	var deadline = new Date("jun 22, 2019 2:20:25").getTime(); 
	  
	var x = setInterval(function() { 
	  
	var now = new Date().getTime(); 
	var t = deadline - now; 
	var days = Math.floor(t / (1000 * 60 * 60 * 24)); 
	var hours = Math.floor((t%(1000 * 60 * 60 * 24))/(1000 * 60 * 60)); 
	var minutes = Math.floor((t % (1000 * 60 * 60)) / (1000 * 60)); 
	var seconds = Math.floor((t % (1000 * 60)) / 1000); 
	document.getElementById("day").innerHTML =days ; 
	document.getElementById("hour").innerHTML =hours; 
	document.getElementById("minute").innerHTML = minutes;  
	document.getElementById("second").innerHTML =seconds;  
	if (t < 0) { 
	        clearInterval(x); 
	        document.getElementById("demo").innerHTML = "TIME UP"; 
	        document.getElementById("day").innerHTML ='0'; 
	        document.getElementById("hour").innerHTML ='0'; 
	        document.getElementById("minute").innerHTML ='0' ;  
	        document.getElementById("second").innerHTML = '0'; } 
	}, 1000); 


}


