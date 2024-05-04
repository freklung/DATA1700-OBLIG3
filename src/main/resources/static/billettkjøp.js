//definerer billett utenfor scope av funksjoner som null
let billett = null;

function getBilleter(){
    //henter billetter fra liste i kontrolleren
    $.get("http://localhost:8080/getBilletter", function(data){
        let billetVisning = "<p>";
        //viser billetter på skjermen fra kontroller
        data.forEach(function (billett){
            billetVisning = "<p>" + billett.fValg + ", "
                + billett.antall + ", "
                + billett.fNavn + ", "
                + billett.eNavn + ", "
                + billett.tlfNr + ", "
                + billett.epost + ", "
                //knapper for å endre og slette billetter
                + "<button class='btn-success' onclick='oppdaterBillett(" + billett.id + ")'>Endre Billett</button>"
                + "<button class='btn-danger' onclick='slettBillett(" + billett.id + ")'>Slett Billett</button>"
        });
        billetVisning += "</p>"
        document.getElementById("billettVisning").innerHTML = billetVisning;
    })
}

//fuknsjonen tar inn en ny billett fra input, sjekker den imot inputvalidering
function kjøpBillett(){
    //sjekker om input er valid mot regexp
    if (!validerInput()) {
        alert("Vennligst fyll ut alle feltene korrekt før du kjøper billett.");
        return;
    }

    //definerer innholdet i billett-objektet ut ifra input
    billett = {
        "fValg": document.getElementById("fValg").value,
        "antall": document.getElementById("antall").value,
        "fNavn": document.getElementById("fNavn").value,
        "eNavn": document.getElementById("eNavn").value,
        "tlfNr": document.getElementById("tlfNr").value,
        "epost": document.getElementById("epost").value
    }

    console.log(billett)

    //sender billett-objektet til databasen
    $.post("http://localhost:8080/nyBilletIDB", billett, function (data) {}
    )

    //bruker jquery til å nullstille inputfeltene
    $("#fValg").val("");
    $("#antall").val("");
    $("#fNavn").val("");
    $("#eNavn").val("");
    $("#tlfNr").val("");
    $("#epost").val("");
}

//validerer input ut ifra regex
function validerInput() {
    const antallValid = document.getElementById("antall");
    const fNavnValid = document.getElementById("fNavn");
    const eNavnValid = document.getElementById("eNavn");
    const tlfNrValid = document.getElementById("tlfNr");
    const epostValid = document.getElementById("epost");

    const tlfRegexp = /^\d+$/;
    const epostRegexp = /\S+@\S+\.\S+/;

    const antallError = document.getElementById("antallerror");
    const fnavnError = document.getElementById("fnavnerror");
    const enavnError = document.getElementById("eNavnerror");
    const tlfError = document.getElementById("tlferror");
    const epostError = document.getElementById("eposterror");

    if (!antallValid.value || antallValid.value <= 0) {
        antallError.textContent = "Du må velge et gyldig antall billetter.";
        return false;
    } else {
        antallError.textContent = "";
    }

    if (!fNavnValid.value.match(/^[a-zA-Z]+$/)) {
        fnavnError.textContent = "Du må angi et gyldig fornavn.";
        return false;
    } else {
        fnavnError.textContent = "";
    }

    if (!eNavnValid.value.match(/^[a-zA-Z]+$/)) {
        enavnError.textContent = "Du må angi et gyldig etternavn.";
        return false;
    } else {
        enavnError.textContent = "";
    }

    if (!tlfNrValid.value.match(tlfRegexp)) {
        tlfError.textContent = "Du må angi et gyldig telefonnummer.";
        return false;
    } else {
        tlfError.textContent = "";
    }

    if (!epostValid.value.match(epostRegexp)) {
        epostError.textContent = "Du må angi en gyldig e-postadresse.";
        return false;
    } else {
        epostError.textContent = "";
    }
    return true;
}
//tar inn id for valgt billett og sender input i delen av siden for endringer
function oppdaterBillett(id){
    //document.addEventListener("DOMContentLoaded", function (){
        document.getElementById("billettId").innerHTML = id;
        $.get("http://localhost:8080/getBilletterFraDB?id="+id, function (data) {
            console.log(data)
            id = $("#id").val();
            fValg = $("#fvalg").val();
            antall = $("#antall").val();
            fNavn = $("#fNavn").val();
            eNavn = $("#eNavn").val();
            tlfNr = $("#tlfNr").val();
            epost = $("#epost").val();
        })

    //oppdaterer billett i kontrollereh
    $.post("/oppdaterBillett", billett, function (){})
    //})
}

function oppdaterIDB(){
    //definerer nytt billett-objekt og sender det til databasen
    billett = {
        "id": document.getElementById("billettId").innerHTML,
        "fValg": document.getElementById("endrefValg").value,
        "antall": document.getElementById("endreantall").value,
        "fNavn": document.getElementById("endrefNavn").value,
        "eNavn": document.getElementById("endreeNavn").value,
        "tlfNr": document.getElementById("endretlfNr").value,
        "epost": document.getElementById("endreepost").value
    }
    console.log(billett);
    $.post("http://localhost:8080/oppdaterBillett", billett, function (data){})
}

function slettBillett(id){
    $.ajax({
        url: "http://localhost:8080/slettBillett?id="+id,
        type: 'DELETE',
        success: function(){
            $("#visBillett").html("");
            console.log("Billet med id " + id + " slettet")
        }
    });
}