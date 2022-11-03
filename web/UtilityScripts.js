var UtilityScripts = (function () {




    return {

        updateAttendee: function () {
            var that = this;
            $("#output").append("got to updateAttendee");
            //get value of the attendee id
            var id = $("#attendeemenu").val().trim();

            //get new value for the firstname,lastname,displayname
            var firstname = $("#firstname").val().trim();
            var lastname = $("#lastname").val().trim();
            var displayname = $("#displayname").val().trim();
            data = { "firstname": firstname, "lastname": lastname, "attendeeid": id, "displayname": displayname };

            //ajax put request to attendess serverlet
            $.ajax({
                url: 'http://localhost:8180/Lab6/Attendees',
                method: 'PUT',
                data: data,
                dataType: 'json',
                success: function (response) {
                    //that.success(response);
                    if (response.success === true)
                        //directly output response to output div
                        $("#output").html("Update saved successfully!");
                }
            });



        },
        getSessionInfo: function () {
            console.log("get Session Info");
            var sessionid = $("#sessionmenu").val().trim();
            var url = 'http://localhost:8180/Lab6/TrainingSessionServerlet?id=' + sessionid;
            $.ajax({
                url: url,
                method: 'GET',
                success: function (response) {
                    //that.success(response);
                    if (response[0].success === true) {

                        //directly output response to output div
                        console.log(response);
                        //add table of the json information
                        //$("#output").append(JSON.stringify(response));
                        var test = "<tr><th>Attendee Id</th><th>Session Id</th><th>Firstname</th><th>Lastname</th><th>Display Name</th></tr>";
                        for (let i = 1; i < response.length; i++) {

                            test += "<tr>";
                            for (const key in response[i]) {
                                console.log(key, response[i][key]);
                                test += "<td>";
                                test += response[i][key];
                                test += "</td>";
                            }
                            console.log("-----------------------");
                            test += "</tr>";

                        }
                        console.log(test);
                        $("#outputtable").html(test);
                    }
                }
            });

        },

        updateRegistration: function () {
            console.log("update Registration");
        },

        deleteRegistration: function () {
            console.log("delete registration");
        },

    };
})();