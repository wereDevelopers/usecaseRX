# UseCaseRX

[![Jitpack](https://jitpack.io/v/wereDevelopers/usecaseRX.svg)](https://jitpack.io/#wereDevelopers/usecaseRX)
[![API](https://img.shields.io/badge/API-23%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=23)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/wereDevelopers/usecaseRX/blob/main/LICENSE)

UseCaseRX is used for managing threads in apps while maintaining clean architecture

## How to implement:

add in the Gradle

```groovy
dependencies {
    implementation('com.github.wereDevelopers:usecaseRX:{LastTag}')
}
```


## How to use



### Activity:
```

class MainActivity : AppCompatActivity() {

    private lateinit var homeViewModel: HomeViewModel
	...
```


### ViewModel:
```

class HomeViewModel : BaseViewModel() {
    private val repoCache = RepositoryCacheImpl()
    private val repoBE = RepositoryBackEndImpl()
    private val getMessageFromBackEndUseCase = GetMessageFromBackEndUseCase(repoBE)
    private val getMessageFromCacheUseCase = GetMessageFromCacheUseCase(repoCache)

    val messageBELiveData: MutableLiveData<Resource<String>> = MutableLiveData()

    fun getMessageFromBackEnd(id: String) {
        getMessageFromBackEndUseCase.executeAndDispose(messageBELiveData, id)
    }

    fun getMessageCache(id: String) {
        getMessageFromCacheUseCase.invoke(id)
    }
}
```
