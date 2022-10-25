import { useState } from 'react'
import { Grid,  Button, TextField,  Tab, Tooltip,  CircularProgress } from '@mui/material'

function AtualizarCliente({ requisicao, cliente, setLinhas, setAbaAtual }) {
    console.log('cliente')
    console.log(cliente)
    const [ form, setForm ] = useState({
        nome: cliente.nome,
        email: cliente.email,
        senha: cliente.senha,
        cpf: cliente.cpf,
        dataDeNascimento: cliente.dataDeNascimento,
    });

    const handleChange = (campo, value) => (
        setForm({
            ...form,
            [campo]: value,
        })
    );

    return (
        <div>
        <h2>Cadastrar novo Cliente</h2>
          <Grid container xs={12}direction="column" justifyContent='center' spacing={2}>
            <Grid item>
            <TextField fullWidth label='Nome Completo' value={form.nome} onChange={(e) => handleChange('nome', e.target.value)} variant='outlined'></TextField>
            </Grid>
            <Grid item>
            <TextField fullWidth label='Email' value={form.email} onChange={(e) => handleChange('email', e.target.value)} variant='outlined'></TextField>
            </Grid>
            <Grid item>
            <TextField fullWidth label='Senha' value={form.senha} onChange={(e) => handleChange('senha', e.target.value)} variant='outlined'></TextField>
            </Grid>
            <Grid item>
            <TextField fullWidth label='CPF' value={form.cpf} onChange={(e) => handleChange('cpf', e.target.value)} variant='outlined'></TextField>
            </Grid>
            <Grid item>
            <TextField fullWidth label='Data de Nascimento' value={form.dataDeNascimento} onChange={(e) => handleChange('dataDeNascimento', e.target.value)} variant='outlined'></TextField>
            </Grid>
            <Grid item>
                <Button style={{
                    outline: 'none',
                    fontWeight: 'bold'
                }}
                variant='contained'
                onClick={async () => {
                    await requisicao('PUT', form, `/api/clientes/${cliente.id}`)
                    const data = await requisicao('GET', null, '/api/clientes');
                    setLinhas(data)
                    setAbaAtual('get-all-clientes')
                }}
                > 
                Enviar Requisição </Button>
            </Grid>
          </Grid>
      </div>
    )
}

export default AtualizarCliente


