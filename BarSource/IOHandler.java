package BarSource;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IOHandler {

    private static IOHandler instance = null;
    private PrintWriter _output;
    private String _time;
    private List<String> data;

    private IOHandler(String _path) throws IOException
    {
            File _file = new File(_path);
            this.data = new ArrayList<>();

            this._output = new PrintWriter(new FileWriter(_file, true));
            this._time = new SimpleDateFormat("[HH:mm]").format(new Date());

            _file.createNewFile();
    }

    public static IOHandler CreateLogSystem(String path) throws IOException
    {
        if(instance == null)
        {
            instance = new IOHandler(path);
        }

        return instance;
    }

    public void save()
    {
        data.forEach(d -> _output.println(d));
        _output.close();
    }

    public void log(String _msgToFile)
    {
        if(_msgToFile == null)
            throw new NullPointerException("Msg can't be null");

        data.add(_time + " " + _msgToFile);
    }
}
