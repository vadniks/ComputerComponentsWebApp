import * as G from './global.js'

let chosenId = -1
const cmVw = document.getElementById('cmpVw'),
    ovrl = document.getElementById('overlay'),
    cmpVwExt = document.getElementById('cmpVwExt'),
    cmpVwFtBt = document.getElementById('cmpVwFtBt'),
    cmpVwPic = document.getElementById('cmpVwPic'),
    srch = document.getElementById('srch')

cmpVwFtBt.addEventListener('click', () =>
    G.request(G.ps, `/slc?id=${chosenId}`, () => G.redir(G.ndx)))

srch.addEventListener('keyup', () => {
    const a = srch.value
    if (a.length === 0) return

    const b = document.getElementsByClassName('cmpSlcItm')
    let d = null

    for (const e of b) {
        let c = e.querySelector('#cmpSlcItmNm')

        if (c.textContent.toLowerCase().indexOf(a.toLowerCase()) > -1) {
            if (!d) d = c

            e.style.backgroundImage = 'linear-gradient(to left, #202324, #2f3535, #3d4545, #2f3535, #202324)'
            setTimeout(() => e.style.backgroundImage = 'none', 2000)
        }
    }
    if (d) d.scrollIntoView()
})

function openOrCloseDialog(a) {
    cmVw.style.display = a ? 'flex' : 'none'
    ovrl.style.display = a ? 'initial' : 'none'
}

window.onItemClick = function onItemClick(_id) {
    const id = parseInt(_id)

    chosenId = id
    cmpVwExt.addEventListener('click', () => openOrCloseDialog(false))

    G.request(G.gt, `/cmp?id=${id}`, (jsn) => {
        openOrCloseDialog(true)

        cmVw.querySelector('#cmpVwNm').textContent = jsn.name
        cmVw.querySelector('#cmpVwDsc').textContent = jsn.description
        cmpVwPic.src = `../../res_back/${jsn.image}.jpg`
    })
}
