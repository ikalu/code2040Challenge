require 'HTTParty'

class Haystack

  def getCollection
    stack = HTTParty.post( "http://challenge.code2040.org/api/haystack", 
                          :body => { "token"=> "8EBDqKCWgK"}.to_json, 
                          :headers => { 'Content-Type' => 'application/json' } )

    stack["result"]
  end

  def findNeedle
    collection = getCollection
    print(collection)
    haystack = collection["haystack"]
    needle = collection["needle"]
    haystack.index(needle)
  end

  def validateNeedle
    result = HTTParty.post( "http://challenge.code2040.org/api/validateneedle",
                                   :body => { "token" => "8EBDqKCWgK", "needle" => findNeedle }.to_json,
                                   :headers => { 'Content-Type' => 'application/json' } )
    print(result)
  end

  def print(someThing)
    puts someThing
  end
end

haystack = Haystack.new
haystack.validateNeedle
