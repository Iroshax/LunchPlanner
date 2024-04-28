import React, { useEffect, useState } from 'react';
import axios from "axios";
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { ToastContainer } from 'react-toastify';
import { useTable, usePagination } from 'react-table';
import NavBar from '../common/NavBar';

export default function SessioonDetails() {
  const [sessions, setSessions] = useState([]);

  useEffect(() => {
    loadSessions();
  }, []);

  const loadSessions = () => {
    axios.get('http://localhost:8087/api/lunchPlanner/session')
      .then(response => setSessions(response.data))
      .catch(error => {
        console.error('Error fetching session data:', error);
        toast.error(`${error.response.data.Message}`);
      });
  };

  const data = React.useMemo(() => sessions, [sessions]);

  const columns = React.useMemo(() => [
    {
      Header: 'Session Id',
      accessor: (row, i) => i + 1,
    },
    {
      Header: 'Session Admin',
      accessor: 'sessionAdmin',
    },
    {
      Header: 'Session Name',
      accessor: 'sessionName',
    },
    {
      Header: 'Selected Restaurant',
      accessor: 'pickedRestaurant',
    },
    {
      Header: 'Status',
      accessor: 'sessionStatus',
    },
  ], []);

  const {
    getTableProps,
    getTableBodyProps,
    headerGroups,
    prepareRow,
    page,
    canPreviousPage,
    canNextPage,
    nextPage,
    previousPage,
  } = useTable(
    {
      columns,
      data,
      initialState: { pageIndex: 0 },
    },
    usePagination
  );

  return (
    <div>
    <NavBar></NavBar>
    <div className='container'>&nbsp;
      <ToastContainer position="top-center"/>
      <h4>Session Details</h4>
      <div className='py-4'>
        <table {...getTableProps()} className="table border shadow">
          <thead>
            {headerGroups.map(headerGroup => (
              <tr {...headerGroup.getHeaderGroupProps()}>
                {headerGroup.headers.map(column => (
                  <th {...column.getHeaderProps()}>{column.render('Header')}</th>
                ))}
              </tr>
            ))}
          </thead>
          <tbody {...getTableBodyProps()}>
            {page.map((row, i) => {
              prepareRow(row);
              return (
                <tr {...row.getRowProps()} className={(row.original.sessionStatus === 'Open' ? "table-success" : '')}>
                  {row.cells.map(cell => (
                    <td {...cell.getCellProps()}>{cell.render('Cell')}</td>
                  ))}
                </tr>
              );
            })}
          </tbody>
        </table>
        <div>
          <button onClick={() => previousPage()} disabled={!canPreviousPage}>
            {'<'}
          </button>{' '}
          <button onClick={() => nextPage()} disabled={!canNextPage}>
            {'>'}
          </button>
        </div>
      </div>
    </div>
    </div>
  );
}