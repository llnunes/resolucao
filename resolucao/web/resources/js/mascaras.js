/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function mascara(o, f) {
    v_obj = o;
    v_fun = f;
    setTimeout("execmascara()", 1);
}

function execmascara() {
    v_obj.value = v_fun(v_obj.value);
}

function valor(v) {
    v = v.replace(/\D/g, "");
    v = v.replace(/[0-9]{15}/, "inválido");
    v = v.replace(/(\d{1})(\d{11})$/, "$1.$2"); // coloca ponto antes dos
    // Ãºltimos 11 digitos
    v = v.replace(/(\d{1})(\d{8})$/, "$1.$2"); // coloca ponto antes dos
    // Ãºltimos 8 digitos
    v = v.replace(/(\d{1})(\d{5})$/, "$1.$2"); // coloca ponto antes dos
    // Ãºltimos 5 digitos
    v = v.replace(/(\d{1})(\d{1,2})$/, "$1,$2"); // coloca virgula antes dos
    // Ãºltimos 2 digitos
    return v;
}

function mtel(v){
    v=v.replace(/\D/g,"");             //Remove tudo o que não é dígito
    v=v.replace(/^(\d{2})(\d)/g,"($1) $2"); //Coloca parênteses em volta dos dois primeiros dígitos
    v=v.replace(/(\d)(\d{4})$/,"$1-$2");    //Coloca hífen entre o quarto e o quinto dígitos
    return v;
}

function formatar(src, mask) {
    var i = src.value.length;
    var saida = mask.substring(0, 1);
    var texto = mask.substring(i);
    if (texto.substring(0, 1) != saida)
    {
        src.value += texto.substring(0, 1);
    }
}