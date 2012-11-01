using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Net.Sockets;
using System.Net;


namespace JoinUsServer
{
    partial class JoinUsServer 
    {
        /// <summary>
        /// 初始化IP终结点 ServerSocket和数据库
        /// </summary>
        private static void initialization ( )
        {
            iped = new IPEndPoint(IPAddress.Any, 3001);
            ServerSocket = new Socket(iped.AddressFamily, SocketType.Stream, ProtocolType.Tcp);
            try
            {
                ServerSocket.Bind(iped);
                ServerSocket.Listen(100);
                db = new JSDB();
            }
            catch (SocketException)
            {
                Console.WriteLine("服务端已开启!");
                System.Environment.Exit(0);
            }
                
        }

        /// <summary>
        /// 判断命令行类型并返回结果
        /// </summary>
        /// <param name="cmd"></param>
        /// <returns></returns>
        private static cmdtype judgecmd ( string cmd )
        {
            if (cmd.Contains("select"))
            {
                return cmdtype.query;
            }
            else
                return cmdtype.nonquery;
        }

        /// <summary>
        /// 进行接收和返回信息
        /// </summary>
        private static void dowork ( )
        {
            Socket s = ClientSocket;
            Byte[] inbuffer=new Byte[1024];
            Byte[] outbuffer;

            IPEndPoint ipendpoint = (IPEndPoint)s.RemoteEndPoint;
            Console.WriteLine(ipendpoint.Address.ToString() + ":" + ipendpoint.Port.ToString() + "连接过来了");

            string inbufferstr;
            string outbufferstr;
            try
            {
                while (true)
                {
                    try
                    {
                        s.Receive(inbuffer);

                        inbufferstr = Encoding.UTF8.GetString(inbuffer);

                        inbufferstr.Trim();

                        inbufferstr = stringhandler(inbufferstr);

                        if (judgecmd(inbufferstr) == cmdtype.query)
                        {
                            outbufferstr = db.ExecuteSelectCmd(inbufferstr);

                            outbuffer = Encoding.UTF8.GetBytes(outbufferstr);
                            s.Send(outbuffer, outbuffer.Length, SocketFlags.None);
                            // outbuffer = Encoding.UTF8.GetBytes(outbufferstrList);
                            // s.Send(outbuffer, outbuffer.Length, SocketFlags.None);
                        }
                        else
                        {
                            db.ExecuteNonQueryCmd(inbufferstr);
                        }
                    }
                    catch (NullReferenceException)
                    {
                    }
                    catch (MySql.Data.MySqlClient.MySqlException)
                    {
                        Console.WriteLine("语句输入错误!");
                    }
                    catch (ArgumentOutOfRangeException)
                    {
                        Console.WriteLine("语句输入错误!");
                    }
                    catch (InvalidOperationException)
                    {
                        Console.WriteLine("不可执行的操作!");
                    }
                }
            }

            catch
            {
                Console.WriteLine("客户端已关闭!");
            }
        }

        static string stringhandler ( string s )
        {
            string result = "";
            int index = s.IndexOf(";");
            result = s.Remove(index);
            return result;
        }
    }
}
