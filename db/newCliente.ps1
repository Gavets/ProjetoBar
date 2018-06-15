$c = import-csv ./nomes.csv -Delimiter "," -Encoding Default
$out = "List<Cliente> cs = new ArrayList<>();`n"
$rndList = @()
$c | % {
    $numSocio = ""
    $isSocio = (Get-Random -Minimum 0 -Maximum 2) -eq 1
    if($isSocio) {
        $numSocio = Get-Random -Minimum 1000 -Maximum 9999
        while($rndList -contains $numSocio) {
            $numSocio = Get-Random -Minimum 1000 -Maximum 9999
        }
        $rndList += $numSocio
    }
    $isSocio = if($isSocio) {"true"} else {"false"}
    $out += "cs.add(new Cliente(`"$($_.Nome)`", $($_.Idade), `"$($_.CPF)`", $($_.Genero), $isSocio, `"$numSocio`"));`n"
}
$out | Out-File criaClientes.txt