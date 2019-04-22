function () {
    var config = { // base config JSON
      appUrl: karate.properties['appUrl']
    };
    karate.log('baseUrl system property was:', config.appUrl);

    return config;
}