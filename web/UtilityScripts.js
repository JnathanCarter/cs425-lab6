var UtilityScripts = (function () {

  


    return {
        
    updateAttendee: function(){
        var that  = this;
        $("#output").append("got to updateAttendee");
        //get value of the attendee id
        var id =$("#attendeemenu").val().trim();

        //get new value for the firstname,lastname,displayname
        var firstname = $("#firstname").val().trim();
        var lastname = $("#lastname").val().trim();
        var displayname = $("#displayname").val().trim();
    data = {"firstname":firstname, "lastname":lastname, "attendeeid":id,"displayname":displayname};

        //ajax put request to attendess serverlet
        $.ajax({
            url: 'http://localhost:8180/Lab6/Attendees',
            method: 'PUT',
            data:data,
            dataType: 'json',
            success: function (response) {
                //that.success(response);
                if (response.success === true)
                    $("#output").html("Update saved successfully!");
            }
        });

    
        //directly output response to output div

    },
    success: function(response){
        $("#output").append(response);

    }

    };
})();