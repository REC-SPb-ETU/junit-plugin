let setHidden = (element, hide) => {
    element.style.display = hide ? 'none' : ''
}

let isSeparationEnabled = document.getElementById('enable-separate-trends')
let isShowDeadNodes = document.getElementById('show-dead-nodes')

let aggregatedChart = document.getElementById('aggregated-chart')
let nodewiseCharts = document.getElementById('separate-charts')
let nonExistingNodeCharts = nodewiseCharts.querySelectorAll(`[existing-node="false"]`)

isSeparationEnabled.checked = false
isShowDeadNodes.checked = true
isShowDeadNodes.disabled = true
setHidden(nodewiseCharts, true)

isSeparationEnabled.addEventListener('change', e => {
    isChecked = e.currentTarget.checked

    isShowDeadNodes.disabled = !isChecked
    setHidden(aggregatedChart, isChecked)
    setHidden(nodewiseCharts, !isChecked)
})

isShowDeadNodes.addEventListener('change', e => {
    isChecked = e.currentTarget.checked

    nonExistingNodeCharts.forEach(c => setHidden(c, !isChecked))
})