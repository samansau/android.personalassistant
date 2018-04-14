package android.dev.personalassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.dev.personalassistant.R;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.WebView;

public class ShowDocumentsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_documents_list);
    }

    public void addDocuments(View view){
        Intent intent=new Intent(this,ShowDocumentDetailsActivity.class);
        startActivity(intent);

    }

//    private void showDocumentInWebView(){
//        PDFImage.sShowImages = true; // show images
//        PDFPaint.s_doAntiAlias = true; // make text smooth
//        HardReference.sKeepCaches = true; // save images in cache
//
//        //Setup webview
//        wv = (WebView)findViewById(R.id.webView1);
//        wv.getSettings().setBuiltInZoomControls(true);//show zoom buttons
//        wv.getSettings().setSupportZoom(true);//allow zoom
//        //get the width of the webview
//        wv.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
//        {
//            @Override
//            public void onGlobalLayout()
//            {
//                ViewSize = wv.getWidth();
//                wv.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//            }
//        });
//
//        try
//        {
//            File file = new File(Environment.getExternalStorageDirectory().getPath() + "/randompdf.pdf");
//            RandomAccessFile f = new RandomAccessFile(file, "r");
//            byte[] data = new byte[(int)f.length()];
//            f.readFully(data);
//            pdfLoadImages(data);
//        }
//        catch(Exception ignored)
//        {
//        }
//    }
//
//    //Load Images:
//    private void pdfLoadImages(final byte[] data)
//    {
//        try
//        {
//            // run async
//            new AsyncTask<Void, Void, String>()
//            {
//                // create and show a progress dialog
//                ProgressDialog progressDialog = ProgressDialog.show(MainActivity.this, "", "Opening...");
//
//                @Override
//                protected void onPostExecute(String html)
//                {
//                    //after async close progress dialog
//                    progressDialog.dismiss();
//                    //load the html in the webview
//                    wv.loadDataWithBaseURL("", html, "text/html","UTF-8", "");
//                }
//
//                @Override
//                protected String doInBackground(Void... params)
//                {
//                    try
//                    {
//                        //create pdf document object from bytes
//                        ByteBuffer bb = ByteBuffer.NEW(data);
//                        PDFFile pdf = new PDFFile(bb);
//                        //Get the first page from the pdf doc
//                        PDFPage PDFpage = pdf.getPage(1, true);
//                        //create a scaling value according to the WebView Width
//                        final float scale = ViewSize / PDFpage.getWidth() * 0.95f;
//                        //convert the page into a bitmap with a scaling value
//                        Bitmap page = PDFpage.getImage((int)(PDFpage.getWidth() * scale), (int)(PDFpage.getHeight() * scale), null, true, true);
//                        //save the bitmap to a byte array
//                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                        page.compress(Bitmap.CompressFormat.PNG, 100, stream);
//                        byte[] byteArray = stream.toByteArray();
//                        stream.reset();
//                        //convert the byte array to a base64 string
//                        String base64 = Base64.encodeToString(byteArray, Base64.NO_WRAP);
//                        //create the html + add the first image to the html
//                        String html = "<!DOCTYPE html><html><body bgcolor=\"#b4b4b4\"><img src=\"data:image/png;base64,"+base64+"\" hspace=10 vspace=10><br>";
//                        //loop though the rest of the pages and repeat the above
//                        for(int i = 2; i <= pdf.getNumPages(); i++)
//                        {
//                            PDFpage = pdf.getPage(i, true);
//                            page = PDFpage.getImage((int)(PDFpage.getWidth() * scale), (int)(PDFpage.getHeight() * scale), null, true, true);
//                            page.compress(Bitmap.CompressFormat.PNG, 100, stream);
//                            byteArray = stream.toByteArray();
//                            stream.reset();
//                            base64 = Base64.encodeToString(byteArray, Base64.NO_WRAP);
//                            html += "<img src=\"data:image/png;base64,"+base64+"\" hspace=10 vspace=10><br>";
//                        }
//                        stream.close();
//                        html += "</body></html>";
//                        return html;
//                    }
//                    catch (Exception e)
//                    {
//                        Log.d("error", e.toString());
//                    }
//                    return null;
//                }
//            }.execute();
//            System.gc();// run GC
//        }
//        catch (Exception e)
//        {
//            Log.d("error", e.toString());
//        }
//
//    }
}
