function fillJunit(trendConfiguration, jsonConfiguration) {
  const useBlue = jsonConfiguration['useBlue'];
  trendConfiguration.find('#junit-use-blue').prop('checked', !!useBlue);

  const nodewise = jsonConfiguration['nodewise'];
  trendConfiguration.find('#junit-nodewise').prop('checked', !!nodewise);
}

function saveJunit(trendConfiguration) {
  return {
    'useBlue': trendConfiguration.find('#junit-use-blue').is(':checked'),
    'nodewise': trendConfiguration.find('#junit-nodewise').is(':checked')
  };
}

echartsJenkinsApi.configureTrend('junit', fillJunit, saveJunit);
