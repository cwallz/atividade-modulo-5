import { useState } from 'react'
import { Grid,  Button, Tab,  CircularProgress } from '@mui/material'
import { TabPanel, TabList, TabContext } from '@mui/lab'
import IconButton from '@mui/material/IconButton';
import DeleteIcon from '@mui/icons-material/Delete';
import UpdateIcon from '@mui/icons-material/DriveFileRenameOutline';
import TabelaClientes from './components/TabelaClientes';
import AtualizarCliente from './components/AtualizarCliente';
import CadastroCliente from './components/CadastroCliente';
import axios from 'axios'

import './App.css'

function App() {

  const [abaAtual, setAbaAtual ] = useState('get-all-clientes');
  const [ linhas, setLinhas ] = useState([]);
  const [ clienteParaAtualizar, setClienteParaAtualizar ] = useState();

  const mudarDeAba = (evento, value) => {
    setAbaAtual(value)
  }

  const requisicao = async (metodo, parametros, caminho) => {
     const { data } = await axios({method: metodo, url: `http://localhost:8080${caminho}`, data: parametros})
    return data;
  }

  const colunas = [
    { field: 'id', headerName: 'ID', width: 30 },
    { field: 'nome', headerName: 'NOME', width: 130 },
    { field: 'email', headerName: 'EMAIL', width: 200 },
    { field: 'senha', headerName: 'SENHA', width: 100 },
    { field: 'cpf', headerName: 'CPF', width: 110 },
    { field: 'dataDeNascimento', headerName: 'DATA DE NASCIMENTO', width: 130 },
    { field: 'Ações', headerName: 'AÇÕES', renderCell: (cellValues) => (
      <>
      <IconButton aria-label="delete">
        <DeleteIcon onClick={async () => {
          await requisicao('DELETE', null, `/api/clientes/${cellValues.id}`);
          const data = await requisicao('GET', null, '/api/clientes');
          setLinhas(data)
        }} />
      </IconButton>
      <IconButton aria-label='update'>
        <UpdateIcon onClick={() => {
          setClienteParaAtualizar(cellValues.row)
          setAbaAtual('update-cliente')
        }} />
      </IconButton>
      </>
    ) }
  ]

  const endpoints = [
    {
      title: 'Listar todos os Clientes',
      method: 'GET',
      params: [],
      returnType: "Retorna uma lista de objetos de Clientes e seus campos",
      path: '/api/clientes',
      value: 'get-all-clientes',
      Component: <div>
          <h2>Lista de Clientes</h2>
          <TabelaClientes 
            linhas={linhas} 
            colunas={colunas} 
            requisicao={requisicao} 
            setLinhas={setLinhas}
          />
        </div>
    },
    {
      title: 'Atualizar Cliente',
      method: 'PUT',
      returnType: "Atualiza um cliente",
      path: '/api/clientes/id',
      value: 'update-cliente',
      hidden: true,
      Component: <AtualizarCliente 
        requisicao={requisicao} 
        cliente={clienteParaAtualizar} 
        setAbaAtual={setAbaAtual} 
        setLinhas={setLinhas}
      />
    },
    {
      title: 'Cadastrar novo Cliente',
      method: 'POST',
      params: [
        {
          name: 'nome',
          type: 'Body',
          valueType: 'string',
          required: true,
          obs: 'Nome do Cliente'
        },
        {
          name: 'email',
          type: 'Body',
          valueType: 'string',
          required: true,
          obs: 'Email do Cliente'
        }
      ],
      returnType: "Retorna uma lista de objetos de Clientes e seus campos",
      path: '/api/clientes',
      value: 'create-cliente',
      Component: <CadastroCliente requisicao={requisicao} />
    }
  ]

  const selectedEndpoint = endpoints.findIndex(({ value }) => abaAtual === value);

     return (
    <div className="App">
      <Grid sx={{ width: 1000 }}>
        <TabContext value={abaAtual}>
          <TabList onChange={mudarDeAba}>
            {endpoints.map((endpoint, index) =>(
                !endpoint.hidden && <Tab 
                  key={index}
                  style={{ outline: 'none'}} 
                  label={endpoint.title}
                  value={endpoint.value}
                >
              </Tab>
              ))}
          
          </TabList>
          {endpoints[selectedEndpoint].Component}
        </TabContext>
      </Grid>
    </div>
  )
}

export default App
