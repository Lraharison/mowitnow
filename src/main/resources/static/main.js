$.fn.extend({
    initGround: function(size, row, cell){
    	this.html('');
        row ++;
        cell ++;
        for ( y=0; y<= row ; y++ ){
            var row_element = $('<div></div>');
            if(y<row){
                axis_y = (row-1)-y ;
                row_element.addClass('ground-row row-' + axis_y);
                row_element.append($('<div></div>').addClass('axis-y').html(axis_y).css({'height':size/cell,'line-height': (size/cell)+ 'px'}));
                for ( x=0; x< cell ; x++ ){
                    row_element.append($('<div></div>').addClass('ground-cell cell-' + x).css({'height':size/cell,'width':size/cell }));
                }
            }
            else {
                row_element.css({'margin-top':2});
                for ( x=0; x< cell ; x++ ){
                    row_element.append($('<div></div>').addClass('axis-x').html(x).css({'width':size/cell}));
                }
            }
            row_element.appendTo(this);
        }
    },
    loadLawnmower:function (element, init, movement) {
        var ground = this;
        var direction = init.orientation.toLowerCase();
        var position = ground.find('.row-'+init.y+' .cell-'+init.x);
        var time = 2000;
        position.addClass('lawnmower-'+element+'-'+direction);
        $.each(movement, function (index, value) {
            setTimeout( function(){
                direction = value.orientation.toLowerCase();
                position.removeClass (function (i, css) {
                    return (css.match (/(^|\s)lawnmower-\S+/g) || []).join(' ');
                });
                position.addClass('toggle');
                var new_position = ground.find('.row-'+value.y+' .cell-'+value.x);
                new_position.addClass('lawnmower-'+element+'-'+direction);
                position = new_position;
            }, time);
            time += 2000;

        });

    }
});
$(function() {
	
	$('.glyphicon-play').click(function() {
		var parent = $(this).parents('tr');
		var elmt = parent.find('td:first-child');
		var val = elmt.text();
		$.ajax({ 
			   type: "GET",
			   dataType: "json",
			   url: "http://localhost:9000/calculate?name="+val,
			   async: false,
			   success: function(data){
				   
				   var wait = 0;
				    var lawnmower = {
				        'red':{
				            'init':data.firstMowerPos,
				            'movement':data.firstMowerAction
				        },
				        'blue' : {
				            'init':data.secondMowerPos,
				            'movement':data.secondMowerAction
				        }
				    };
				    $('.ground').initGround(700,data.upRightCornerPos.y,data.upRightCornerPos.x);
				    $.each(lawnmower, function (index, value) {
				        setTimeout( function(){
				            $('.ground').loadLawnmower(index,value.init, value.movement);
				        }, wait);
				        wait = 2000*data.secondMowerAction.length;
				    });
				  
			   },
			   failed:function(){
				   alert('erreur de creation de terrain');
			   }
			});
	}); 
   

});