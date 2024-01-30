package com.mpierucci.android.oktastartupissue

import android.content.Context
import androidx.startup.Initializer
import com.okta.authfoundation.AuthFoundationDefaults
import com.okta.authfoundation.client.OidcClient
import com.okta.authfoundation.client.OidcConfiguration
import com.okta.authfoundation.client.SharedPreferencesCache
import com.okta.authfoundation.credential.CredentialDataSource.Companion.createCredentialDataSource
import com.okta.authfoundationbootstrap.CredentialBootstrap
import okhttp3.HttpUrl.Companion.toHttpUrl

class OktaInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        AuthFoundationDefaults.cache = SharedPreferencesCache.create(context)
        val oidcConfiguration = OidcConfiguration(
            clientId = "fakeId",
            defaultScope = "openid email profile offline_access",
        )

        val client = OidcClient.createFromDiscoveryUrl(
            oidcConfiguration,
            "https://fakeurl.com".toHttpUrl(),
        )
        CredentialBootstrap.initialize(client.createCredentialDataSource(context))
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}