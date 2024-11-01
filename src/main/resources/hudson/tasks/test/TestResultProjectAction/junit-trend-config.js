function fillJunit(trendConfiguration, jsonConfiguration) {
  const useBlue = jsonConfiguration['useBlue'];
  trendConfiguration.find('#junit-use-blue').prop('checked', !!useBlue);
}

function saveJunit(trendConfiguration) {
  return {
    'useBlue': trendConfiguration.find('#junit-use-blue').is(':checked'),
    'nodeName': trendConfiguration.find('#junit-node-name').prop('value')
  };
}

echartsJenkinsApi.configureTrend('junit', fillJunit, saveJunit);
