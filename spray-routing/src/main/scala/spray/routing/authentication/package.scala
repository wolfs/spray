/*
 * Copyright (C) 2011-2013 spray.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package spray.routing

import scala.concurrent.{ Promise, ExecutionContext, Future }
import com.typesafe.config.{ Config, ConfigException }
import spray.caching.{ Cache, LruCache }
import spray.util.pimpString

package object authentication {

  type ContextAuthenticator[T] = RequestContext ⇒ Future[Authentication[T]]
  type Authentication[T] = Either[Rejection, T]
  type UserPassAuthenticator[T] = Option[UserPass] ⇒ Future[Option[T]]

}

package authentication {

  /**
   * Simple case class model of a username/password combination.
   */
  case class UserPass(user: String, pass: String)

  /**
   * A very basic user context object.
   * In your application you probably want to use some more specific custom class.
   */
  case class BasicUserContext(username: String)

}