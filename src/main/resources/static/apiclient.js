apiclient = (function () {
    var localhost = "http://localhost:8080";

    function getWeatherOfACity(city_name, callback) {

        const promise = new Promise((resolve, reject) => {
            $.ajax({
                url: localhost + "/weather/" + city_name,
                type: 'GET',
                contentType: "application/json"
            }).done(function (response) {
                resolve(response);

            }).fail(function (msg) {
                reject(msg);
            });
        });

        promise
            .then(res => {
                callback(res);
            })
            .catch(error => {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'Error connecting with the API',
                })
            });

    }

    return {
        getWeatherOfACity: getWeatherOfACity,
    }

})();

