using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Net;
using System.Net.Sockets;
using System.Threading;

namespace JoinUsServer
{
    [Serializable]
    partial class JoinUsServer
    {
        private enum cmdtype
        {
            query,
            nonquery                      
        };
        private static Socket ServerSocket;
        private static  Socket ClientSocket;
        private static IPEndPoint iped;
        private static JSDB db;
        private static Thread thread;

        static void Main ( string[] args )
        {
            initialization();
            while (true)
            {
                ClientSocket = ServerSocket.Accept();
                thread = new Thread(new ThreadStart(dowork));
                thread.Start();
            }
        }

        
    }
}
