#include <iostream>
#include "File.h"
#include "Image.h"
#include "Text.h"
#include <vector>


//main file to test Image, Text, and File class
std::vector<File*> filterFiles (const std::vector<File*>& files, std::string extension);

int main( )
{
    std::vector<File*> vecOfFiles;
    vecOfFiles.push_back(new Image("Derick's First File", 10, 10, 16));
    vecOfFiles.push_back(new Text("Derick's Second File",100));
    vecOfFiles.push_back(new Image("Derick's Third File", 20, 10, 32));
    vecOfFiles.push_back(new Text("Derick's fourth File",33));
    vecOfFiles.push_back(new Image("Derick's fifth File", 240, 330, 3));

    std::cout << "\n\nTesting filterFiles():\n";
    std::vector<File*> testingFilter = filterFiles(vecOfFiles, "txt");
    for(int i = 0; i < testingFilter.size();i++) {
        std::cout << testingFilter[i] << std::endl;
    }
    return 0;
}

std::vector<File*> filterFiles(const std::vector<File*>& files, std::string extension)
{
    std::vector<File*> tempFiles(files);
    File* lastElement = tempFiles.back();
    if(tempFiles.size() == 0)
        return tempFiles;
    else{
    tempFiles.pop_back();
    filterFiles(tempFiles,extension);
    }
}
