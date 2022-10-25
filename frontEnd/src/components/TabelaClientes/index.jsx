import { DataGrid } from '@mui/x-data-grid'
import { Button } from '@mui/material'


function TabelaClientes({ linhas, colunas, setLinhas, requisicao }) {
    return (
    <>
        <Button style={{
            outline: 'none',
            fontWeight: 'bold'
        }}
        variant='contained'
        onClick={async () => {
            const data = await requisicao('GET', null, '/api/clientes')
            setLinhas(data)
        }}
        > 
        Atualizar Lista </Button>
        <DataGrid
            style={{ height: 500, width: '100%'}}
            rows={linhas}
            columns={colunas}
            pageSize={20}
            rowsPerPageOptons={[10]}
        />
        </>
    )
}

export default TabelaClientes
