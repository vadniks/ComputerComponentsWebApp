import * as G from './global.js'

const act1 = elem('act1'),
    act2 = elem('act2'),
    ovrl = elem('overlay'),
    flx = 'flex',
    nn = 'none',
    nt = 'initial',
    flds1 = act1.querySelectorAll('input, textarea'),
    flds2 = act2.querySelectorAll('input, textarea'),
    swt = elem('dbCtSw'),
    act1Nm = act1.querySelector('.actNm'),
    act2Nm = act2.querySelector('.actNm'),
    act1FtBt = act1.querySelector('.actFtBt'),
    act2FtBt = act2.querySelector('.actFtBt')

let act1Sh = false, act2Sh = false, curId = -1, swtch = false, mode = 0

function actNm(isUsr, nm) { if (!isUsr) {
    act1Nm.textContent = nm
    act1FtBt.textContent = nm
} else {
    act2Nm.textContent = nm
    act2FtBt.textContent = nm
} }

function orc(isUsr) { if (!isUsr) {
    act1Sh = !act1Sh
    act1.style.display = act1Sh ? flx : nn
    ovrl.style.display = act1Sh ? nt : nn
} else {
    act2Sh = !act2Sh
    act2.style.display = act2Sh ? flx : nn
    ovrl.style.display = act2Sh ? nt : nn
} }
window.orc = orc

window.onSwitchClick = function onSwitchClick() {
    swtch = !swtch
    swt.style.backgroundColor = !swtch ? 'darkslategrey' : 'gray'
    swt.textContent = !swtch ? 'components' : 'users'
}

function clr(isUsr) { if (!isUsr) {
    flds1[0].value = ''
    flds1[1].value = ''
    flds1[2].value = ''
    flds1[3].value = ''
    flds1[4].value = ''
    flds1[5].value = ''
} else {
    flds2[0].value = ''
    flds2[1].value = ''
    flds2[2].value = ''
    flds2[3].value = ''
    flds2[4].value = ''
    flds2[5].value = ''
    flds2[6].value = ''
    flds2[7].value = ''
    flds2[8].value = ''
} }

function idDsb(dsb, isUsr) { (!isUsr ? flds1[0] : flds2[0]).disabled = dsb }

window.onSelectClick = function onSelectClick() {
    if (act1Sh || act2Sh) return

    orc(swtch)
    actNm(swtch, 'Select')
    clr(swtch)
    idDsb(false, swtch)
    mode = 0
}

window.onInsertClick = function onInsertClick() {
    if (act1Sh || act2Sh) return

    orc(swtch)
    actNm(swtch, 'Insert')
    clr(swtch)
    idDsb(true, swtch)
    mode = 1
}

window.onUpdateClick = function onUpdateClick(id, isUsr) {
    if (act1Sh || act2Sh) return; G.request(
        G.gt, `/${!isUsr ? 'cmp' : 'usr'}?id=${id}`, jsn =>
    {
        orc(isUsr)
        actNm(isUsr, 'Update')
        idDsb(true, isUsr)
        if (!isUsr) {
            flds1[0].value = jsn.id
            flds1[1].value = jsn.type
            flds1[2].value = jsn.name
            flds1[3].value = jsn.description
            flds1[4].value = jsn.cost.substring(0, jsn.cost.length - 1)
            flds1[5].value = jsn.image
        } else {
            flds2[0].value = jsn.id
            flds2[1].value = jsn.name
            flds2[2].value = jsn.role
            flds2[3].value = jsn.password
            flds2[4].value = jsn.firstName
            flds2[5].value = jsn.lastName
            flds2[6].value = jsn.phone
            flds2[7].value = jsn.address
            flds2[8].value = jsn.selections
        }
        curId = parseInt(jsn.id)
        mode = 2
    })
}

function nie(a) { return a.length === 0 ? null : a }

window.act = function act(isUsr) {
    const a = curId === -1, b = mode === 0
    const jsn = !isUsr ? {
        id: !a ? parseInt(flds1[0].value) : null,
        type: flds1[1].value,
        name: flds1[2].value,
        description: flds1[3].value,
        cost: flds1[4].value,
        image: flds1[5].value
    } : {
        id: !a ? parseInt(flds2[0].value) : null,
        name: flds2[1].value,
        role: flds2[2].value,
        password: flds2[3].value,
        firstName: nie(flds2[4].value),
        lastName: nie(flds2[5].value),
        phone: nie(flds2[6].value),
        address: nie(flds2[7].value),
        selections: nie(flds2[8].value)
    }
    if (!b)
        G.request(G.ps, `/iou${!isUsr ? 'c' : 'u'}`, rld, JSON.stringify(jsn))
    else {
        let bwh, slc
        const fld = (wh, nx) => {
            const a = wh[nx].value, b = a.length > 0
            if (b) slc = a
            return b
        }

        if (!isUsr) switch (true) {
            case fld(flds1, 0): bwh = 'id'; break
            case fld(flds1, 1): bwh = 'type'; break
            case fld(flds1, 2): bwh = 'name'; break
            case fld(flds1, 3): bwh = 'description'; break
            case fld(flds1, 4): bwh = 'cost'; break
            case fld(flds1, 5): bwh = 'image'; break
        } else switch (true) {
            case fld(flds2, 0): bwh = 'id'; break
            case fld(flds2, 1): bwh = 'name'; break
            case fld(flds2, 2): bwh = 'role'; break
            case fld(flds2, 3): bwh = 'password'; break
            case fld(flds2, 4): bwh = 'firstName'; break
            case fld(flds2, 5): bwh = 'lastName'; break
            case fld(flds2, 6): bwh = 'phone'; break
            case fld(flds2, 7): bwh = 'address'; break
            case fld(flds2, 8): bwh = 'selections'; break
        }

        G.redir(`/adm?entity=${!isUsr}&byWhich=${bwh}&selection=${slc}`)
    }
}

window.onDeleteClick = function onDeleteClick(id, isUsr) { G.request(
    G.ps, `/rmv?entity=${!isUsr}&id=${id}`, rld
) }

function elem(id) { return document.getElementById(id) }
function rld() { window.location.reload() }

///////////////////////////////////////////////////////////////////////////////////

window.onload = () => { setTimeout(() => {
    document.getElementById('cntFtr').style.height =
        '' + document.getElementById('cntHdr').clientHeight + 'px'

    const cntHdr = document.getElementById('cntHdr'),
        _dbs = document.querySelector('.dbs'),
        ht = cntHdr.clientHeight,
        cnt = document.getElementById('content')

    window.addEventListener('scroll', () => {
        if (window.pageYOffset > cnt.offsetTop) {
            cntHdr.style.position = 'fixed'
            cntHdr.style.top = '0'
            cntHdr.style.width = window.innerWidth > 950 ? '90%' : '98%'
            _dbs.style.marginTop = ht + 'px'
        } else {
            cntHdr.style.position = 'relative'
            _dbs.style.marginTop = '0'
            cntHdr.style.width = '100%'
        }
    })
}, 0) }
