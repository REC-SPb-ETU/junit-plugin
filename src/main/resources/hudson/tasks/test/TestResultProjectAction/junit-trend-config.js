let getTrendConfigPrefixByidx = (idx) => {
  return `${idx}-junit`
}

let fillJunit = (trendConfiguration, jsonConfiguration, chartIdx) => {
  const useBlue = jsonConfiguration['useBlue']
  trendConfiguration.find(`${chartIdx}-junit-use-blue`).prop('checked', !!useBlue)
}

let saveJunit = (trendConfiguration, chartIdx) => {
  return {
    'useBlue': trendConfiguration.find(`#${chartIdx}-junit-use-blue`).is(':checked'),
    'nodeName': trendConfiguration.find(`#${chartIdx}-junit-node-name`).prop('value')
  }
}

let numCharts = document.getElementsByClassName('echarts-trend').length
for (let i = 0; i < numCharts; i++) {
  echartsJenkinsApi.configureTrend(
    getTrendConfigPrefixByidx(i),
    (tc, jc) => fillJunit(tc, jc, i),
    (tc) => saveJunit(tc, i)
  )
}