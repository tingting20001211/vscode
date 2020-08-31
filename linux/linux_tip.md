find or search
grep
    -n  match line num
    -v  not match line num
    -i  ignore word big and small
e.g.
    grep content filename
    grep -n content filename
model Regular expression find
    ^a  font
    a$  tail
e.g. 
    grep -n m$ filename




echo 
    In[1]: echo hello
    $ hello

重定向>> 和 >
    >    表示输出，会覆盖原有的内容
    >>   表示追加，会将内容追加到已有的文件末尾

e.g. 
    echo hello > filename
    echo hello >> filename
    ls -lh > filename 
    tree ~ > filename
    tree ~ > ~/Workplace/linux/tree_file   [find tree_file]


管道 |
    linux允许 `一个命令的输出` 可以通过管道 作为 `另一个命令的输入`

e.g.
    tree  / | more
    ls -lha ~ | grep content


远程管理


关机/重启
    shutdowm 选项 时间
        -r  重启
        shutdown -r now     [now重启] 
        shutdown -c         [取消]
        shutdown now        [now关机]
        shutdown time
        shutdown +10        [10min关机]
    e.g.
    # 重新启动操作系统，其中 now 表示现在
    $ shutdown -r now
    
    # 立刻关机，其中 now 表示现在
    $ shutdown now

    # 系统在今天的 20:25 会关机
    $ shutdown 20:25
    
    # 系统再过十分钟后自动关机
    $ shutdown +10
    
    # 取消之前指定的关机计划
    $ shutdown -c




查看或配置网卡信息
    网卡是一个专门负责网络通讯的硬件设备
    IP地址是设置在网卡上的地址信息

      ifconfig  
      ping ip

      127.0.0.1  本地回环地址，用来测试本机网卡是否正常


远程登陆和复制文件
    ssh --> Secure Shell 
    scp --> Secure Copy 

scp  是一个在linux下用来远程拷贝文件的命令
他的地址格式与SSH基本相同，需要注意的是，在指定的端口时用的是大写的-P，而不是小写的-p


域名
    有一串用点分割的名字组成，例如www.baidu.com, 是IP地址的别名

端口号
    IP地址：通过ip地址找到网络上的计算机
    端口号： 通过端口号可以找到计算机上运行的应用程序
        * SSH服务器的默认端口号是22，如果是默认端口号，在连接的时候，可以忽略

    常见的服务端口列表
    序号 服务       端口号
    01   SSH服务器  22
    02   Web服务器  80
    03   HTTPS      443
    04   FTP服务器  21


SSH客户端的简单使用
    ssh [-p port] user@remote
        user 是远程机器上的用户名，如果不指定的话，默认为当前用户
        remote 是远程机器的地址，可以是IP/域名，或者是后面提到的别名
        port  是SSH Server监听的端口，如果不指定，就为默认值22

    使用exit退出当前用户的登录

e.g.
    ssh -p 22 root@192.168.241.130



scp  是一个在linux下用来`远程拷贝文件`的命令
    地址格式与SSH基本相同
    -> 需要注意的是，在指定的端口时,用的是大写的-P，而不是小写的-p

example:
    把本地当前目录下的 01.py文件复制到远程家目录下的Desktio/01.py
    scp -P port 01.py user@remote:Desktop/01.py
        注意 `:`后面的路径如果不是绝对路径，则以用户的家目录作为参照路径

    把远程家目录下的Desktop/01.py 文件复制到本地当前目录下的01.py
    scp -P port user@remote:Desktop/01.py 01.py

    加上 -r 选项可以传送文件夹
    把当前目录下的demo文件夹复制到远程家目录下的Desktop
    scp -r demo user@remote:Desktop

    把远程家目录下的Desktop复制到当前目录下的demo文件夹
    scp -r user@remote:Desktop demo

        e.g.
        file:

        remote --> local  
        scp -P 22 root@192.168.241.130:Desktop/filename .
        
        local  --> remote
        scp -P 22 filename root@192.168.241.130:Desktop

        
        files:

        remote --> local
        scp -P 22 -r root@192.168.241.130:Desktop demo


    在window下使用filezilla 进行ftp协议的文件传输
        port : 22  
        ip : 192.168.241.130
        username : root
        password : kali

    ssh的高级 -->  貌似kali的配置不太一样
        - 免密码登录
        - 配置别名
        *有关于ssh的配置信息都保存在家目录下的.ssh目录下

    strp 1)
        配置公钥
            *执行 ssh-keygen 即可生成 SSH 钥匙，一路回车即可
        上传公钥到服务器
            *执行 ssh-copy-id -p port user@remote ，可以让远程服务器记住我们的公钥


        2) 配置别名   
            每次都输入 ssh -p port user@remote ,会很麻烦, user,remote和port都得输入，而配置别名 ssh other_name 来替代
                ~/.ssh/config 里面追加以下内容：
                保存之后，即可用 ssh other_name 实现远程登录了， scp 同样可以使用
                Host other_uesrname
                    HostName ip地址
                    User username
                    Port 22

    远程登录方式
        ssh other_username
    文件传输
        scp -r local/file other_username:file

    用户 和 权限 的基本概念
    1.1 基本概念
    -用户 是Linux 系统工作中重要的一环,用户管理包括`用户`与`组`管理      
    在Linux系统中,不论是由本机或是远程登录系统,每个系统都必须拥有一个账号,并且对于不同的系统资源拥有不同的使用权限
    在 Linux 中,可以指定 每一个用户 针对 不同的文件或者目录 的 不同权限
        对 文件／目录 的权限包括：
        序号   权限   英文     缩写 数字代号
        01     读     read     r     4
        02     写     write    w     2
        03     执行   excute   x     1

        ls -l 可以查看文件夹下文件的详细信息，从左到右依次是：
        -权限 --> 第1个字符如果是d表示目录
        -硬链接数，通俗地讲，就是有多少种方式，可以访问到当前目录／文件
        -拥有者，家目录下 文件／目录 的拥有者通常都是当前用户
        -组，在 Linux 中,很多时候,会出现组名和用户名相同的情况,后续会讲
        大小
        时间
        名称
        
        drwxr-xr-x 2 root root 4096 Jun 11 15:19 Desktop
        drwxr-xr-x 2 root root 4096 Mar 20 12:05 Documents
        drwxr-xr-x 2 root root 4096 Mar 20 12:05 Downloads
        drwxr-xr-x 2 root root 4096 Mar 20 12:05 Music
        drwxr-xr-x 2 root root 4096 Mar 20 12:05 Pictures
        drwxr-xr-x 2 root root 4096 Mar 20 12:05 Public


        chmod 使用
        chmod 可以修改 用户／组 对 文件／目录 的权限
        命令格式如下：
            chmod +/-rwx 文件名|目录名
           提示：以上方式会一次性修改 拥有者/组 权限，

    超级用户root
        ->Linux 系统中的 root 账号通常用于系统的维护和管理，对操作系统的所有资源 具有所有访问权限
        ->在大多数版本的 Linux 中，都不推荐 直接使用 root账号登录系统
        ->在Linux安装的过程中，系统会自动创建一个用户账号，而这个默认的用户就称为“标准用户”
        * sudo
        ->su 是 substitute user 的缩写，表示 使用另一个用户的身份
        ->sudo 命令用来以其他身份来执行命令，预设的身份为 root
        ->用户使用 sudo 时，必须先输入密码，之后有 5 分钟的有效期限，超过期限则必须重新输入密码
            *-->若其未经授权的用户企图使用 sudo ，则会发出警告邮件给管理员


    组管理 终端命令
        提示：创建组 / 删除组 的终端命令都需要通过 sudo 执行
        序号   命令                        作用
        01     groupadd 组名               添加组
        02     groupdel 组名               删除组
        03     cat /etc/group              确认组信息
        04     chgrp -R 组名 文件/目录名   递归修改文件/目录的所属组

    提示：
        组信息保存在 /etc/group 文件中
        /etc 目录是专门用来保存 系统配置信息 的目录

    e.g.
        add group -> sudo groupadd group_name
        del group -> sudo groupdel group_name
        change group -> sudo chgrp -R group_name files/filename

    
        
    用户管理 终端命令
    提示：创建用户 / 删除用户 / 修改其他用户密码 的终端命令都需要通过 sudo 执行

    创建用户／设置密码／删除用户

    序号   命令                              作用         说明
    01     useradd -m -g 组 新建用户名    添加新用户   1. -m 自动建立用户家目录
    -->                                                2. -g 指定用户所在的组，否则会建立一个和同名的组
    02     passwd 用户名                 设置用户密码  如果是普通用户，直接用 passwd 可以修改自己的账户密码
    03     userdel -r 用户名              删除用户      -r 选项会自动删除用户家目录
    04     cat /etc/passwd | grep 用户名  确认用户信息  新建用户后，用户信息会保存在 /etc/passwd文件中

    提示：
        创建用户时，如果忘记添加 -m 选项指定新用户的家目录 —— 最简单的方法就是删除用户，重新创建
        创建用户时，默认会创建一个和用户名同名的组名,用户信息保存在 /etc/passwd 文件中


    查看用户信息

    序号 命令          作用
    01   id [用户名]   查看用户 UID 和 GID 信息      UID->/etc/passwd   GID->/etc/group
    02   who           查看当前所有登录的用户列表
    03   whoami        查看当前登录用户的账户名

    passwd 文件
        /etc/passwd 文件存放的是用户的信息，由 6 个分号组成的 7 个信息，分别是
        1. 用户名
        2. 密码（x，表示加密的密码）
        3. UID（用户标识）
        4. GID（组标识）
        5. 用户全名或本地帐号
        6. 家目录
        7. 登录使用的 Shell，就是登录之后，使用的终端命令， ubuntu 默认是 dash


    usermod
        +usermod 可以用来设置 用户 的 主组 ／ 附加组 和 登录 Shell，命令格式如下：
        +主组：通常在新建用户时指定，在 etc/passwd 的第 4 列 GID 对应的组
        +附加组：在 etc/group 中最后一列表示该组的用户列表，用于指定 用户的附加权限
        -> 提示：设置了用户的附加组之后，需要重新登录才能生效！
   
    # 修改用户的主组（passwd 中的 GID）
    usermod -g 主组 用户名

    # 修改用户的附加组
    usermod -G 附加组 用户名
    
    # 修改用户登录 Shell
    usermod -s /bin/bash 用户名

    注意：默认使用 useradd 添加的用户是没有权限使用 sudo 以 root 身份执行命令的，可以使用以下命令，将用户添加到 sudo 附加组中
    --> usermod -G sudo 用户名

    
    which（重要）
    *提示
        /etc/passwd 是用于保存用户信息的文件
        /usr/bin/passwd 是用于修改用户密码的程序
    
    -->* which 命令可以查看执行命令所在位置，例如：
    which ls
    
    # 输出
    # /bin/ls
    
    which useradd
    # 输出
    # /usr/sbin/useradd

    which sl
    # 输出
    # /usr/games/sl

    bin 和 sbin
    在 Linux 中，绝大多数可执行文件都是保存在 /bin 、 /sbin 、 /usr/bin 、 /usr/sbin
    /bin （ binary ）是二进制执行文件目录，主要用于具体应用
    /sbin （ system binary ）是系统管理员专用的二进制代码存放目录，主要用于系统管理
    /usr/bin （ user commands for applications ）后期安装的一些软件
    /usr/sbin （ super user commands for applications ）超级用户的一些管理程序
   
    提示：
    cd 这个终端命令是内置在系统内核中的，没有独立的文件，因此用 which 无法找到 cd命令的位置

    
    切换用户 -> su
    序 号   命令         作用                         说明
    01      su - 用户名  切换用户，并且切换目录  -可以切换到用户家目录，否则保持位置不变
    11      su  用户名   切换用户,不换目录
    02      exit         退出当前登录账户
    
    --> su 不接用户名，可以切换到 root ，但是不推荐使用，因为不安全


     修改文件权限
     序号   命令    作用
     01     chown   修改拥有者
     02     chgrp   修改组
     03     chmod   修改权限
     
     命令格式如下:

     # 修改文件|目录的拥有者
     chown 用户名 文件名|目录名
     
     # 递归修改文件|目录的组
     chgrp -R 组名 文件名|目录名
     
     # 递归修改文件权限
     chmod -R 755 文件名|目录名

     chmod 在设置权限时，可以简单地使用三个数字分别对应 拥有者 ／ 组 / 其他 用户的权限

     # 直接修改文件|目录的 读|写|执行 权限，但是不能精确到 拥有者|组|其他
     chmod +/-rwx 文件名|目录名

      拥有者     组    其他用户
      r w x     r w x   r w x
      4 2 1     4 2 1   4 2 1

      421 -> 7 rwx
      420 -> 6 rw- 
      401 -> 5 r-x
      400 -> 4 r--
      021 -> 3 -wx
      020 -> 2 -w-
      001 -> 1 --x
      000 -> 0 ---

     常见数字组合有（ u 表示用户／ g 表示组／ o 表示其他）：
     chmod +/-rwx 文件名|目录名
        777 ==> u=rwx,g=rwx,o=rwx
        755 ==> u=rwx,g=rx ,o=rx
        644 ==> u=rw ,g=r  ,o=r

系统信息相关命令
    ->本节内容主要是为了方便通过远程终端维护服务器时，查看服务器上当前 系统日期和时间 ／ 磁盘空间占用情况 ／ 程序执行情况
    ->本小结学习的终端命令基本都是查询命令，通过这些命令对系统资源的使用情况有个了解

    时间和日期
        date --> 查看系统时间
        cal  --> calendar 查看日历， -y 选项可以查看一年的日历
    磁盘和目录空间
        df 
            -->df -h       -> disk free 显示磁盘剩余空间
        Filesystem Size  Used   Avail   Use%    Mounted on
        文件系统   大小   已用   可用   已用%     挂载点
        
        du
            -->du -h [目录名]  ->disk usage 显示目录下的文件大小
        -h 以人性化的方式显示文件大小
    
    进程信息
        - 所谓 进程，通俗地说就是 当前正在执行的一个程序
        ps  --> ps aux                -> process status 查看进程的详细状况 
               ps 默认只会显示当前用户通过终端启动的应用程序
          ps 选项说明:
            a 显示终端上的所有进程，包括其他用户的进程
            u 显示进程的详细状态
            x 显示没有控制终端的进程
        top --> top                   -> 动态显示运行中的进程并且排序
            要退出 top 可以直接输入 q
        kill --> kill [-9] 进程代号   ->终止指定代号的进程， -9 表示强行终止
            提示：使用 kill 命令时，最好只终止由当前用户开启的进程，而不要终止 root 身份开启的进程，否则可能导致系统崩溃



    其他命令
    
    查找文件
    ->find

    软链接
    ->ln
    
    打包和压缩
    ->tar
    
    软件安装
    ->apt-get

    查找文件
    find 命令功能非常强大，通常用来在 特定的目录下 搜索 符合条件的文件
序号    命令                         作用
01      find [路径] -name "*.py"     查找指定路径下扩展名是 .py 的文件，包括子目录

    如果省略路径，表示在当前文件夹下查找,之前学习的通配符，在使用 find 命令时同时可用

    1. 搜索桌面目录下，文件名包含 1 的文件
    find ~/Desktop -name "*1*"
    
    2. 搜索桌面目录下，所有以 .txt 为扩展名的文件
    find ~/Desktop -name "*.txt"
    
    3. 搜索桌面目录下，以数字 1 开头的文件
    find ~/Desktop -name "1*"

    软链接
    序号  命令                                       作用
    01    ln -s 被链接的源文件的完整路径  链接文件    建立文件的软链接，用通俗的方式讲类似于 Windows 下的快捷方式

    注意：
    1. 没有 -s 选项建立的是一个 硬链接文件
        ->两个文件占用相同大小的硬盘空间，工作中几乎不会建立文件的硬链接
    2. 源文件要使用绝对路径，不能使用相对路径，这样可以方便移动链接文件后，仍然能够正常使用

    e.g.    -->使用绝对路径
    将~/Workplace/qsort.cpp的cpp文件软链接到桌面
    $ cd ~/Desktop 
    $ ln -s /root/Workplace/cpp/qsort.cpp demo

    硬链接简介
    ->在使用 ln 创建链接时，如果没有 -s 选项，会创建一个 硬链接，而不是软链接
    > 在 Linux 中，文件名 和 文件的数据 是分开存储的
    提示：
        在 Linux 中，只有文件的 硬链接数 == 0 才会被删除
        使用 ls -l 可以查看一个文件的硬链接的数量
        在日常工作中，几乎不会建立文件的硬链接，知道即可


    打包压缩
    打包压缩 是日常工作中备份文件的一种方式
    在不同操作系统中，常用的打包压缩方式是不同的
    ->Windows 常用 rar
    ->Mac 常用 zip
    ->Linux 常用 tar.gz
    
    打包 ／ 解包
    tar 是 Linux 中最常用的 备份工具，此命令可以 把一系列文件 打包到 一个大文件中，也可以把一个 打包的大文件恢复成一系列文件
    --> tar不负责压缩只负责解压和压缩

    tar 的命令格式如下:
    # 打包文件
    tar -cvf 打包文件.tar  被打包的文件／路径...
    # 解包文件
    tar -xvf 打包文件.tar

    tar 选项说明
    选项    含义
    c       生成档案文件，创建打包文件
    x       解开档案文件
    v       列出归档解档的详细过程，显示进度
    f       指定档案文件名称，f 后面一定是 .tar 文件，所以必须放选项最后
       -> 注意： f 选项必须放在最后，其他选项顺序可以随意
       --> tar不负责压缩只负责解压和压缩


